package com.investment.product.repository;

import com.investment.product.ProductApplicationTest;
import com.investment.product.entity.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class ProductRepositoryTest extends ProductApplicationTest {
    @Autowired
    private ProductRepository productRepository;

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

    @Test
    public void test() {
    }
}