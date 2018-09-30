package com.investment.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@Slf4j
@EnableAspectJAutoProxy
public class CommonApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }

    @Value("${spring.application.name:}")
    private String application_name;

    @Value("${spring.cloud.client.ip-address:}")
    private String ip_address;

    @Value("${server.port}")
    private String port;

    @Value("${spring.cloud.client.hostname:}")
    private String hostname;
    @Override
    public void run(String... args) throws Exception {
        log.info("server {} start at http://{}:{} in {}",application_name,ip_address,port,hostname);
    }
}