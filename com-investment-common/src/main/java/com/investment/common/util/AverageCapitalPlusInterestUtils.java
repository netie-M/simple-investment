package com.investment.common.util;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

/**
 * 等额本息相关计算（粗略版）
 * 适用于试算
 */
public class AverageCapitalPlusInterestUtils {
    public static final BigDecimal TWELVE = new BigDecimal("12");
    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    public static final int SCALE_10 = 10;
    public static final int SCALE_2 = 2;
    public static final String PATTEN = "yyyy-MM";

    /**
     * 等额本息
     * @param principal 贷款本金
     * @param month 借款期数
     * @param rate 年利率
     * @return
     * .principal 贷款本金
     * .month 借款期数
     * .rate 年利率
     * .repaymentAmountMonthly 每月还款金额
     * .degressiveAmountMonthly 每月递减金额
     * .interest 总利息
     * .totalAmount 总还款金额
     */
    public static Map<String,Object> calculate(
            BigDecimal principal,
            Integer month,
            BigDecimal rate){
        BigDecimal repaymentAmountMonthly = calculateMonthlyRepayment(principal,month,rate);
        BigDecimal principalPlusInterest = repaymentAmountMonthly.multiply(new BigDecimal(month));
        BigDecimal degressiveAmountMonthly = BigDecimal.ZERO;
        BigDecimal interest = principalPlusInterest.subtract(principal);

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

    public static List<Map<String,Object>> calculate(
            BigDecimal principal,
            Date firstDate,
            Integer month,
            BigDecimal rate){
        List<Map<String,Object>> planList = new ArrayList<>();
        BigDecimal repayment = calculateMonthlyRepayment(principal,month,rate);
        calculate(planList,0,principal,firstDate,month,rate,repayment);
        return planList;
    }

    public static void calculate(
            List<Map<String,Object>> planList,
            int currentTerm,
            BigDecimal previousBalance,
            Date firstDate,
            int totalTerm,
            BigDecimal rate,
            BigDecimal repayment){
        Map<String,Object> map = calculateTerm(currentTerm,previousBalance,firstDate,totalTerm,rate,repayment);
        planList.add(map);
        BigDecimal nextbalance = (BigDecimal)map.get("residual_principal");
        if (nextbalance.compareTo(BigDecimal.ZERO) <= 0 || currentTerm == totalTerm){
           return;
        }else{
            calculate(planList,currentTerm + 1,previousBalance,firstDate,totalTerm,rate,repayment);
        }
    }

    /**
     * 等额本息-每期还款明细计算
     * @param previousTerm 上期期号
     * @param previousBalance 上期未还本金
     * @param firstDate 首次还款日
     * @param totalTerm 总期数
     * @param rate 利率
     * @param repayment 每月还款金额
     * @return
     * .termNo 本次期号
     * .termDate 本期还款日
     * .repayment 本期还款金额
     * .repayment_principal 本期还款本金
     * .repayment_interest 本期还款利息
     * .residual_principal 剩余本金
     */
    public static Map<String,Object> calculateTerm(
            int previousTerm,
            BigDecimal previousBalance,
            Date firstDate,
            int totalTerm,
            BigDecimal rate,
            BigDecimal repayment){
        Map<String,Object> map = new HashMap<>();
        BigDecimal nextbalance = BigDecimal.ZERO;
        if(previousTerm + 1 < totalTerm){
            nextbalance = previousBalance
                    .multiply(TWELVE.add(rate))
                    .divide(TWELVE,SCALE_10,ROUNDING_MODE)
                    .subtract(repayment);
        }

        BigDecimal principal = previousBalance.subtract(nextbalance);
        BigDecimal interest = repayment.subtract(principal);
        map.put("termNo",previousTerm + 1);
        map.put("termDate",new DateTime(firstDate).plusMonths(previousTerm).toString(PATTEN));
        map.put("repayment",repayment);
        map.put("repayment_principal",principal);
        map.put("repayment_interest",interest);
        map.put("residual_principal",nextbalance);
        return map;
    }

    /**
     * 等额本息 - 一次性提前还款
     * @param principal 贷款本金
     * @param month 借款期数
     * @param payTimes 已还期数
     * @param rate 年利率
     * @return
     * .principal 贷款本金
     * .month 借款期数
     * .rate 年利率
     * .repaymentAmountMonthly 每月还款金额
     * .degressiveAmountMonthly 每月递减金额
     * .interest 总利息
     * .totalAmount 总还款金额
     * .residualPrincipal 未还本金
     * .repaidPrincipal 已还本金
     * .repaidAmount 已还总金额
     * .repaidInterest 已还利息
     * .prepaymentAmount 提前还款总额
     * .reduceAmount 节省利息
     * .residualMonth 剩余还款期数
     */
    public static Map<String,Object> calculatePrepayment(
            BigDecimal principal,
            Integer month,
            Integer payTimes,
            BigDecimal rate){
        BigDecimal repaymentAmountMonthly = calculateMonthlyRepayment(principal,month,rate);
        BigDecimal principalPlusInterest = repaymentAmountMonthly.multiply(new BigDecimal(month));
        BigDecimal interest = principalPlusInterest.subtract(principal);

        BigDecimal repaidAmount = repaymentAmountMonthly.multiply(new BigDecimal(payTimes));
        BigDecimal residualPrincipal = calculateResidualPrincipal(principal,repaymentAmountMonthly,payTimes,rate);
        BigDecimal repaidPrincipal = principal.subtract(residualPrincipal);
        BigDecimal repaidInterest = repaidAmount.subtract(repaidPrincipal);
        BigDecimal prepaymentAmount = residualPrincipal.multiply(TWELVE.add(rate)).divide(TWELVE,2,ROUNDING_MODE);
        BigDecimal reduceAmount = principalPlusInterest.subtract(repaidAmount).subtract(prepaymentAmount);

        Integer residualMonth = 0;

        Map<String,Object> map = new HashMap<>();
        map.put("principal",principal);
        map.put("month",month);
        map.put("rate",rate);
        map.put("repaymentAmountMonthly",repaymentAmountMonthly);
        map.put("interest",interest);
        map.put("totalAmount",principalPlusInterest);
        map.put("residualPrincipal",residualPrincipal);
        map.put("repaidAmount",repaidAmount);
        map.put("repaidPrincipal",repaidPrincipal);
        map.put("repaidInterest",repaidInterest);
        map.put("prepaymentAmount",prepaymentAmount);
        map.put("reduceAmount",reduceAmount);
        map.put("residualMonth",residualMonth);
        return map;
    }

    /**
     * 等额本息 - 提前还款 - 期数不变
     * @param principal
     * @param prepaymentAmount 提前还款金额
     * @param month
     * @param payTimes
     * @param rate
     * @return
     */
    public static Map<String,Object> calculatePrepaymentApart1(
            BigDecimal principal,
            BigDecimal prepaymentAmount,
            Integer month,
            Integer payTimes,
            BigDecimal rate){
        BigDecimal repaymentAmountMonthly = calculateMonthlyRepayment(principal,month,rate);
        BigDecimal principalPlusInterest = repaymentAmountMonthly.multiply(new BigDecimal(month));
        BigDecimal interest = principalPlusInterest.subtract(principal);

        BigDecimal repaidAmount = repaymentAmountMonthly.multiply(new BigDecimal(payTimes));
        BigDecimal residualPrincipal = calculateResidualPrincipal(principal,repaymentAmountMonthly,payTimes,rate);
        BigDecimal repaidPrincipal = principal.subtract(residualPrincipal);
        BigDecimal repaidInterest = repaidAmount.subtract(repaidPrincipal);
        BigDecimal nextPayAmount = repaymentAmountMonthly.add(prepaymentAmount);

        residualPrincipal = calculateResidualPrincipal(principal,repaymentAmountMonthly,payTimes + 1,rate)
                .subtract(prepaymentAmount);

        BigDecimal newRepaymentAmountMonthly = calculateMonthlyRepayment(residualPrincipal,month - payTimes - 1,rate);
        BigDecimal residualTotalAmount = newRepaymentAmountMonthly.multiply(new BigDecimal(month - payTimes -1));
        BigDecimal residualInterest = residualTotalAmount.subtract(residualPrincipal);

        BigDecimal reduceAmount =principalPlusInterest.subtract(repaidAmount)
                .subtract(nextPayAmount).subtract(residualTotalAmount);

        Integer residualMonth = month - payTimes;

        Map<String,Object> map = new HashMap<>();
        map.put("principal",principal);
        map.put("month",month);
        map.put("rate",rate);
        map.put("repaymentAmountMonthly",repaymentAmountMonthly);
        map.put("interest",interest);
        map.put("totalAmount",principalPlusInterest);
        map.put("repaidAmount",repaidAmount);
        map.put("repaidPrincipal",repaidPrincipal);
        map.put("repaidInterest",repaidInterest);
        map.put("prepaymentAmount",prepaymentAmount);

        map.put("residualTotalAmount",residualTotalAmount);
        map.put("newRepaymentAmountMonthly",newRepaymentAmountMonthly);
        map.put("residualPrincipal",residualPrincipal);
        map.put("residualInterest",residualInterest);
        map.put("nextPayAmount",nextPayAmount);
        map.put("prepaymentAmount",prepaymentAmount);

        map.put("reduceAmount",reduceAmount);
        map.put("residualMonth",residualMonth);
        return map;
    }

    /**
     * 等额本息 - 提前还款 - 月供不变
     * @param principal
     * @param prepaymentAmount 提前还款金额
     * @param month
     * @param payTimes
     * @param rate
     * @return
     */
    public static Map<String,Object> calculatePrepaymentApart2(
            BigDecimal principal,
            BigDecimal prepaymentAmount,
            Integer month,
            Integer payTimes,
            BigDecimal rate){
        BigDecimal repaymentAmountMonthly = calculateMonthlyRepayment(principal,month,rate);
        BigDecimal principalPlusInterest = repaymentAmountMonthly.multiply(new BigDecimal(month));
        BigDecimal interest = principalPlusInterest.subtract(principal);

        BigDecimal repaidAmount = repaymentAmountMonthly.multiply(new BigDecimal(payTimes));
        BigDecimal residualPrincipal = calculateResidualPrincipal(principal,repaymentAmountMonthly,payTimes,rate);
        BigDecimal repaidPrincipal = principal.subtract(residualPrincipal);
        BigDecimal repaidInterest = repaidAmount.subtract(repaidPrincipal);
        BigDecimal nextPayAmount = repaymentAmountMonthly.add(prepaymentAmount);

        residualPrincipal = calculateResidualPrincipal(principal,repaymentAmountMonthly,payTimes + 1,rate)
                .subtract(prepaymentAmount);
        System.out.println(residualPrincipal);
        Integer residualMonth = calculateMonth(principal,repaymentAmountMonthly,rate) + 1;



        Map<String,Object> map = new HashMap<>();
        map.put("principal",principal);
        map.put("month",month);
        map.put("rate",rate);
        map.put("repaymentAmountMonthly",repaymentAmountMonthly);
        map.put("interest",interest);
        map.put("totalAmount",principalPlusInterest);
        map.put("repaidAmount",repaidAmount);
        map.put("repaidPrincipal",repaidPrincipal);
        map.put("repaidInterest",repaidInterest);
        map.put("prepaymentAmount",prepaymentAmount);

//        map.put("residualTotalAmount",residualTotalAmount);
//        map.put("newRepaymentAmountMonthly",newRepaymentAmountMonthly);
//        map.put("residualPrincipal",residualPrincipal);
//        map.put("residualInterest",residualInterest);
//        map.put("nextPayAmount",nextPayAmount);
//        map.put("prepaymentAmount",prepaymentAmount);
//
//        map.put("reduceAmount",reduceAmount);
        map.put("residualMonth",residualMonth);
        return  map;
    }

    /**
     * 等额本息-计算期数
     * 计算公式: log[每月还款金额/(贷款本金×月利率)] / log[（1＋月利率）]
     *
     * @param principal
     * @param repayment
     * @param rate
     * @return
     */
    public static Integer calculateMonth(
            BigDecimal principal,
            BigDecimal repayment,
            BigDecimal rate){
        BigDecimal value1 = TWELVE.add(rate);
        BigDecimal value2 = TWELVE.multiply(repayment);
        BigDecimal value3 = principal.multiply(rate);
        double a = Math.log10(value2.doubleValue())-Math.log10(value2.subtract(value3).doubleValue());
        double b = Math.log10(value1.doubleValue())-Math.log10(TWELVE.doubleValue());
        return new BigDecimal(Math.ceil(a/b)).intValueExact();
    }

    /**
     * 等额本息-每月还款金额
     * 计算公式:〔贷款本金×月利率×（1＋月利率）＾还款月数〕÷〔（1＋月利率）＾还款月数－1〕
     * @param principal 贷款本金
     * @param month 借款期数
     * @param rate 年利率
     */
    public static BigDecimal calculateMonthlyRepayment(
            BigDecimal principal,
            Integer month,
            BigDecimal rate){
        BigDecimal value1 = TWELVE.add(rate);
        BigDecimal value2 = value1.pow(month);
        BigDecimal value3 = TWELVE.pow(month);
        return principal
                .multiply(rate)
                .multiply(value2)
                .divide(TWELVE.multiply(value2.subtract(value3)),SCALE_10, ROUNDING_MODE);
    }

    /**
     * 等额本息-剩余应还本金
     * 计算公式: 贷款本金 *（1 + 月利率）＾当前还款期数 - [月还款金额 * 〔（1＋月利率）＾当前还款期数 － 1〕/ 月利率]
     *  推导一：当期月剩余应还本金 = 剩余应还本金 * （1＋月利率） - 月还款金额
     *  推导二：
     *  等比数列 ： An = a1 * q＾(n-1)
     *  等比数列求和： Sn = a1 * [1- (1+q)＾n]/(1-q)
     * @param principal 贷款本金
     * @param repaymentAmountMonthly 月还款金额
     * @param payTimes 已归还次数
     * @param rate 年利率
     * @return
     */
    public static BigDecimal calculateResidualPrincipal(
            BigDecimal principal,
            BigDecimal repaymentAmountMonthly,
            Integer payTimes,
            BigDecimal rate){
        BigDecimal value1 = TWELVE.add(rate);
        BigDecimal value2 = value1.pow(payTimes);
        BigDecimal value3 = TWELVE.pow(payTimes);
        BigDecimal value4 = TWELVE.multiply(repaymentAmountMonthly);
        return principal.multiply(rate).multiply(value2).subtract(value4.multiply(value2.subtract(value3)))
                .divide(value3.multiply(rate),2, ROUNDING_MODE);
    }
}
