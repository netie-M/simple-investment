package com.investment.product.controller;

import com.investment.common.enums.ResultObjectCode;
import com.investment.common.vo.ResultObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductOrderController {

    /**
     * <p>扣减产品可用余额</p>
     * @return
     */
    @RequestMapping("/product/withdraw")
    public ResultObject queryProduct(){
        return ResultObject.builder()
                .code(ResultObjectCode.SUCCESS.getCode())
                .msg(ResultObjectCode.SUCCESS.getMsg())
                .data(null)
                .build();
    }

    /**
     * <p>取消扣减产品可用余额</p>
     * @return
     */
    @RequestMapping("/product/Wipeout")
    public ResultObject WipeoutProduct(){
        return ResultObject.builder()
                .code(ResultObjectCode.SUCCESS.getCode())
                .msg(ResultObjectCode.SUCCESS.getMsg())
                .data(null)
                .build();
    }
}
