package com.investment.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class EqualPrincipalAndInterestUtils {
    public static final BigDecimal TWELVE = BigDecimal.valueOf(12l);
    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    /**
     * 等额本金-每月还款金额
     * 计算公式: 每月还款金额 = （贷款本金 ÷ 还款月数）+（本金 — 已归还本金累计额）×每月利率
     * @param principal 贷款本金
     * @param repaidPrincipal 已归还本金
     * @param month 借款期数
     * @param rate 年利率
     */
    public static BigDecimal calculateMonthlyRepaymentEqualPrincipalAndInterest(
            BigDecimal principal,
            BigDecimal repaidPrincipal,
            Integer month,
            BigDecimal rate){
        BigDecimal value1 = new BigDecimal(month);
        return principal
                .subtract(repaidPrincipal)
                .multiply(rate)
                .multiply(value1)
                .add(principal.multiply(TWELVE))
                .divide(value1.multiply(TWELVE),2, ROUNDING_MODE);
    }

    /**
     * 等额本金
     * 总利息计算公式：(还款月数 + 1) * 贷款本金 * 月利率 / 2;
     * 每月递减金额计算公式：(贷款本金/还款月数) * 月利率
     * @param principal 贷款本金
     * @param month 借款期数
     * @param rate 年利率
     * @return
     * .principal 贷款本金
     * .month 借款期数
     * .rate 年利率
     * .repaymentAmountMonthly 首月还款金额
     * .degressiveAmountMonthly 每月递减金额
     * .interest 总利息
     * .totalAmount 总还款金额
     */
    public static Map<String,Object> calculateEqualPrincipalAndInterest(
            BigDecimal principal,
            Integer month,
            BigDecimal rate){
        BigDecimal value1 = BigDecimal.valueOf(2l);
        BigDecimal value2 = BigDecimal.valueOf(month);

        BigDecimal repaymentAmountMonthly = calculateMonthlyRepaymentEqualPrincipalAndInterest(principal,BigDecimal.ZERO,month,rate);
        BigDecimal degressiveAmountMonthly = principal
                .multiply(rate)
                .divide(TWELVE.multiply(value2),2,RoundingMode.HALF_UP);
        BigDecimal interest = value2
                .add(BigDecimal.ONE)
                .multiply(principal)
                .multiply(rate)
                .divide(TWELVE.multiply(value1),2,RoundingMode.HALF_UP);
        BigDecimal principalPlusInterest = principal.add(interest);

        Map<String,Object> map = new HashMap<>();
        map.put("principal",principal);
        map.put("month",month);
        map.put("rate",rate);
        map.put("repaymentAmountMonthly",repaymentAmountMonthly);
        map.put("degressiveAmountMonthly",degressiveAmountMonthly);
        map.put("interest",interest);
        map.put("totalAmount",principalPlusInterest);
        return map;
    }
}
