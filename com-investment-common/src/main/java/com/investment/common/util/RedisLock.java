package com.investment.common.util;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RedisLock {
    public final static String UNLOCK_LUA = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then " +
            "    return redis.call(\"del\",KEYS[1]) " +
            "else " +
            "    return 0 " +
            "end ";

    private static RedisTemplate redisTemplate;

    private RedisLock(){
        throw new IllegalStateException("Utility class");
    }

    /**
     * 加锁并设置超时时间
     * @param key
     * @param value
     * @param expire 超时时间 单位：秒
     * @return
     */
    public static boolean lock(String key, String value, long expire) {
        return (Boolean)redisTemplate.execute(new RedisCallback<Object>() {
            public Boolean doInRedis(RedisConnection connection) {
                Object nativeConnection = connection.getNativeConnection();
                String result = "";
                if (nativeConnection instanceof JedisCluster) {
                    JedisCluster cluster =  (JedisCluster) nativeConnection;
                    result = cluster.set(key,value,"NX","EX",expire);
                }else if (nativeConnection instanceof RedisProperties.Jedis) {
                    Jedis jedis = (Jedis)nativeConnection;
                    result = jedis.set(key,value,"NX","EX",expire);
                }
                return "OK".equalsIgnoreCase(result);
            }
        }, true);
    }

    /**
     * 解锁
     * @param key
     * @param value
     */
    public static boolean unlock(String key,String value){
        List<String> keys = new ArrayList<String>();
        keys.add(key);
        List<String> args = new ArrayList<String>();
        args.add(value);
        Long result = (Long)redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                Object nativeConnection = connection.getNativeConnection();

                if (nativeConnection instanceof JedisCluster) {
                    return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, keys, args);
                } else if (nativeConnection instanceof Jedis) {
                    return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, args);
                }
                return 0L;
            }
        });
        return result != null && result > 0;
    }

    /**
     *
     * @param key
     * @param value
     * @param expire
     * @param wait
     */
    public static boolean tryLock(String key, String value, long expire,long wait) throws InterruptedException{
        if (Thread.interrupted())
            throw new InterruptedException();
        long nanosTimeout = TimeUnit.SECONDS.toNanos(wait);
        if (nanosTimeout <= 0L)
            return false;
        final long deadline = System.nanoTime() + nanosTimeout;
        for (;;) {
            boolean success = lock(key,value,expire);
            if(success){
                return true;
            }
            nanosTimeout = deadline - System.nanoTime();
            if (nanosTimeout <= 0L)
                return false;

            if (Thread.interrupted())
                throw new InterruptedException();
        }
    }
}
