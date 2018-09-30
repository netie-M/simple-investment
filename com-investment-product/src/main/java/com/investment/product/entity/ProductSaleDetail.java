package com.investment.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class ProductSaleDetail extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;
    private String productNo;//#产品编号
    private String productName;//#产品名称
    private String investorName;//#投资人姓名
    private BigDecimal investmentAmount;//#投资金额
    private Date investmentDate;//#投资日期
    private String orderNo;//#订单编号
}
