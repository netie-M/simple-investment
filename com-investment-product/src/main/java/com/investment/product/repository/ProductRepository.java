package com.investment.product.repository;

import com.investment.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @example
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByProductNo(String productNo);
}
