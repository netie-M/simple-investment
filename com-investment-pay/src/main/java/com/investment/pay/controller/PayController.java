package com.investment.pay.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {
    @Value("${server.port}")
    private String port;

    /**
     * 获取服务端口号
     * @return
     */
    @GetMapping("/getPayPort")
    public String getOrderPort() {
        return "pay-service port：" + port;
    }

}
