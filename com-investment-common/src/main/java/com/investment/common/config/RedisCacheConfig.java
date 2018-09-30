package com.investment.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@EnableCaching
@Slf4j
public class RedisCacheConfig extends CachingConfigurerSupport {
//    @Bean
//    CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//        RedisCacheManager cacheManager = RedisCacheManager.create(connectionFactory);
//        cacheManager.afterPropertiesSet();
//        return cacheManager;
//    }
    @Bean
    CacheManager cacheManager(RedisConnectionFactory connectionFactory, RedisTemplate redisTemplate) {
        log.info("RedisCacheManager init...");
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(60))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        redisTemplate.getKeySerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        redisTemplate.getValueSerializer()))
                ;

        Set<String> cacheNames =  new HashSet<>();
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        return  RedisCacheManager.builder(connectionFactory)
                .initialCacheNames(new HashSet<>())
                .withInitialCacheConfigurations(configMap)
                .cacheDefaults(config)
                .build();
    }
}
