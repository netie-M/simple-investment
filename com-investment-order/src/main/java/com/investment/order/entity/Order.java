package com.investment.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String userInfo;//用户信息
    private String orderNo;//订单号
    private String paymentNo;//支付编号
    private String productNo;//产品编号
    private String productName;//产品名称
    private String productRate;//年化收益率
    private Date valueDate;//起息日
    private Date dueDate;//锁定结束日
    private BigDecimal incomeAmout;//收益金额
    private String specialOffersInfo;//优惠信息
    private BigDecimal orderAmount;//订单金额
    private BigDecimal paymentAmount;//支付金额
    private Date creationDate;//创建时间
    private String paymentRemark;//支付信息 银行名称-卡号（后4位）
    private Date paymentDate;//支付时间
    private String paymentMsg;//支付结果信息
    private String receivedPaymentRemark;//回款信息 银行名称-卡号（后4位）
    private Date receivedPaymentDate;//回款时间
    private String orderStatus;//订单状态 1-未付款 2-已付款 3-已回款
    private String tradeStatus;//交易成功 1-交易成功 2-交易关闭 3-交易处理中 4-回款处理中 5-回款成功
}
