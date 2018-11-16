package com.investment.common.util;

import io.netty.util.internal.MathUtil;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InterestUtils {


/*****************************************************************************************************************************************/
    public static void main(String[] args) {
        BigDecimal principal = new BigDecimal("1000000");
        Integer month = 360;
        BigDecimal rate = new BigDecimal("4.9").movePointLeft(2);
        Integer payTimes = 10;
        BigDecimal prepaymentAmount = new BigDecimal("500000");
        List<Map<String,Object>> list = new ArrayList();
        AverageCapitalPlusInterestUtils.calculate(list,0,principal, DateTime.parse("2018-01-01").toDate(),month,rate,new BigDecimal("5307.26721"));
        System.out.println(list);
    }
    public static  NumberFormat FORMAT = NumberFormat.getNumberInstance();
}
