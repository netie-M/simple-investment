package com.investment.common.config;

import com.investment.common.util.InetUtils;
import com.investment.common.util.SnowflakeIdWorker;
import com.investment.common.util.Utility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class IdWorkerConfig {
    @Value("${spring.cloud.client.ip-address:}")
    private String ip_address;

    @Bean
    public SnowflakeIdWorker idWorker(){
        int sid = ip_address.hashCode();
        short pid = InetUtils.createProcessIdentifier();
        long workerId = Utility.bytesToLong(Utility.legacyToBytes(sid,pid));
        return new SnowflakeIdWorker(workerId,1L);
    }
}
