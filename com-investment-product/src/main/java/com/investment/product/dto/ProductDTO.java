package com.investment.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String productNo;//#产品编号
    private String productName;//#产品名称
    private BigDecimal productRate;//#产品年化利率
    private Integer productLockoutDuration;//#产品锁定期限
    private BigDecimal minInvestmentAmount;//#产品起投金额
    private BigDecimal minIncreaseAmount;//#产品累加金额
    private BigDecimal productAmout;//#产品售卖总金额
    private String interestMode;//#产品起息方式 1-立即起息 2-T+1 3-D+1
    private BigDecimal maxInvestmentAmount;//#产品单笔限额
    private String supportRollover;//#产品是否支持续期 0-不支持 1-支持
    private String supportEarlyWithdral;//#产品是否支持提前退出 0-不支持 1-支持
    private String note;//#产品描述
    private BigDecimal saledAmount;//#产品已售金额
    private BigDecimal remainAmount;//#产品剩余可售金额
    private String saleStatus;//#产品售卖状态 0-创建 1-待售 2-在售 3-售罄
    private Date saleStartDate;//#产品开始售卖时间
    private Date saleEndDate;//#产品结束售卖时间
    private Date saleOutDate;//#产品售罄时间
}
