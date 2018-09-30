package com.investment.product.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.investment.product.ProductApplicationTest;
import com.investment.product.entity.Product;
import com.investment.product.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.math.BigDecimal;

public class IProductServiceTest extends ProductApplicationTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired(required = false)
    private Jackson2JsonRedisSerializer serializer;

    @Before
    public void before(){
        productRepository.deleteAll();
        productRepository.save(Product.builder()
                .productNo("A101001011")
                .productName("11号产品")
                .productAmout(new BigDecimal(10000))
                .productRate(new BigDecimal(10))
                .productLockoutDuration(60)
                .maxInvestmentAmount(new BigDecimal(1000))
                .minInvestmentAmount(new BigDecimal(100))
                .minIncreaseAmount(new BigDecimal(10))
                .build());
        productRepository.save(Product.builder()
                .productNo("A101001010")
                .productName("1号产品")
                .productAmout(new BigDecimal(10000))
                .productRate(new BigDecimal(10))
                .productLockoutDuration(60)
                .maxInvestmentAmount(new BigDecimal(1000))
                .minInvestmentAmount(new BigDecimal(100))
                .minIncreaseAmount(new BigDecimal(10))
                .build());
    }

    @Autowired
    private IProductService productService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void queryProduct() throws JsonProcessingException {
        System.out.println(objectMapper == null);
        System.out.println(serializer == null);
        productService.queryProduct("A101001011");
//        productService.queryProduct("A101001011");
//        Product product = (Product)redisTemplate.opsForValue().get("product::product_A101001011");
        Product product = productService.queryProduct("A101001011");
                System.out.println(product.getProductName());
        System.out.println(objectMapper.writeValueAsString(product));
    }
}