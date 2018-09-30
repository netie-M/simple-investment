package com.investment.product.repository;

import com.investment.product.entity.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStatusRepository extends JpaRepository<ProductStatus,Long> {
    ProductStatus findByProductNo(String productNo);
}
