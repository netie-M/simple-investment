package com.investment.common.enums;

import lombok.Getter;
import lombok.Setter;

public enum ResultObjectCode {
    SUCCESS("0000","交易成功"),
    ACCEPT("1000","请求成功"),
    SERVICE_ERROR("5000","服务异常")
    ;

    private ResultObjectCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Getter
    @Setter
    private String code;
    @Getter @Setter private String msg;

    @Override
    public String toString() {
        return this.code;
    }
}
