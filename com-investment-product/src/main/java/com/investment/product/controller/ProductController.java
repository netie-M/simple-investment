package com.investment.product.controller;

import com.investment.common.enums.ResultObjectCode;
import com.investment.common.vo.ResultObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    /**
     * <p>查询单个产品详情</p>
     * @param productNo
     * @return
     */
    @RequestMapping("/product/{productNo}")
    public ResultObject queryProduct(@PathVariable("productNo") String productNo){
        return ResultObject.builder()
                .code(ResultObjectCode.SUCCESS.getCode())
                .msg(ResultObjectCode.SUCCESS.getMsg())
                .data(null)
                .build();
    }

    /**
     * <p>查询在售和待售的产品列表</p>
     * @return
     */
    @RequestMapping("/product/insale")
    public ResultObject queryProductSaleIn(){
        return ResultObject.builder()
                .code(ResultObjectCode.SUCCESS.getCode())
                .msg(ResultObjectCode.SUCCESS.getMsg())
                .data(null)
                .build();
    }

    /**
     * <p>查询最近的已售产品列表（近20个）</p>
     * @return
     */
    @RequestMapping("/product/outsale")
    public ResultObject queryProductSaleOut(){
        return ResultObject.builder()
                .code(ResultObjectCode.SUCCESS.getCode())
                .msg(ResultObjectCode.SUCCESS.getMsg())
                .data(null)
                .build();
    }
}
