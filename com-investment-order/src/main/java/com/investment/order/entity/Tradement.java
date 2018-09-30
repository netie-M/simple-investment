package com.investment.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Tradement {
    private String userNo;//用户编号
    private String orderNo;//订单号
    private String paymentNo;//支付编号
    //用户信息
    private String productNo;//产品编号
    private String productName;//产品名称
    private String specialOffersInfo;//优惠信息
    private BigDecimal tradeAmount;//交易金额金额
    private Date creationDate;//创建时间
    private String tradeType;//交易类型 1-购买 2-回款 3-红包提现 4-体验金收益 5-加息收益 6-红包使用
    private String tradeRemark;//交易成功
    private String remark;//备注
}
