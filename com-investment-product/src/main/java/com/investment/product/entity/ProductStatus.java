package com.investment.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product_status",
        uniqueConstraints = {@UniqueConstraint(name = "unique_product_status_productNo",columnNames="productNo")})
public class ProductStatus extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;
    private String productNo;//#产品编号
    private BigDecimal saledAmount;//#产品已售金额
    private BigDecimal remainAmount;//#产品剩余可售金额

}
