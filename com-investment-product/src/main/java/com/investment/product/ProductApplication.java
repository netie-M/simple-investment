package com.investment.product;

import com.investment.common.config.RedisCacheConfig;
import com.investment.common.config.RedisConfig;
import com.investment.common.util.InetUtils;
import com.investment.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaAuditing
@Slf4j
@Import({RedisCacheConfig.class,RedisConfig.class})
public class ProductApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @Value("${spring.application.name:}")
    private String application_name;

    @Value("${spring.cloud.client.ip-address:}")
    private String ip_address;

    @Value("${spring.cloud.client.hostname:}")
    private String hostname;

    @Value("${server.port}")
    private String port;

    @Autowired
    private ProductRepository productRepository;
    @Override
    public void run(String... args) throws Exception {
        System.out.println(InetUtils.createProcessIdentifier());
        log.info("server {} start at http://{}:{} on {}", application_name, ip_address, port, hostname);
    }
}