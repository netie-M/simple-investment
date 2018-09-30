package com.investment.product.enums;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

public enum SaleStatus {
    SALE_STATUS_OUT("3","售罄"),
    SALE_STATUS_IN("2","在售"),
    SALE_STATUS_WAIT("1","待售"),
    SALE_STATUS_CREATE("0","创建");

    private SaleStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Getter @Setter private String code;
    @Getter @Setter private String msg;

    @Override
    public String toString() {
        return this.code;
    }

    public static SaleStatus getEnumByCode(String code) {
        return  Arrays.stream(SaleStatus.values())
                .filter(enumEntiy -> StringUtils.equalsIgnoreCase(code,enumEntiy.getCode()))
                .findFirst()
                .orElse(null);
    }
}
