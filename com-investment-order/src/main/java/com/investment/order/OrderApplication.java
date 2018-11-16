package com.investment.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class OrderApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Value("${spring.application.name:}")
    private String application_name;

    @Value("${spring.cloud.client.ip-address:}")
    private String ip_address;

    @Value("${spring.cloud.client.hostname:}")
    private String hostname;

    @Value("${server.port}")
    private String port;

    @Override
    public void run(String... args) throws Exception {
        log.info("server {} start at http://{}:{} on {}",application_name,ip_address,port,hostname);
    }
}
