package com.investment.product.controller;

import com.investment.common.enums.ResultObjectCode;
import com.investment.common.vo.ResultObject;
import com.investment.product.dto.ProductDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductConsoleController {

    /**
     * <p>查询所有的产品列表</p>
     * @return
     */
    @RequestMapping("/product/all")
    public ResultObject queryAllProduct(){
        return ResultObject.builder()
                .code(ResultObjectCode.SUCCESS.getCode())
                .msg(ResultObjectCode.SUCCESS.getMsg())
                .data(null)
                .build();
    }

    /**
     * <p>新建产品</p>
     * @return
     */
    @RequestMapping(value = "/product/all",method = RequestMethod.POST)
    public ResultObject createProduct(ProductDTO productDTO){
        return ResultObject.builder()
                .code(ResultObjectCode.SUCCESS.getCode())
                .msg(ResultObjectCode.SUCCESS.getMsg())
                .data(null)
                .build();
    }

    /**
     * <p>查询单个产品详情</p>
     * @param productId
     * @return
     */
    @RequestMapping(value = "/product/{productId}",method = RequestMethod.GET)
    public ResultObject queryProduct(@PathVariable("productId") String productId){
        return ResultObject.builder()
                .code(ResultObjectCode.SUCCESS.getCode())
                .msg(ResultObjectCode.SUCCESS.getMsg())
                .data(null)
                .build();
    }

    /**
     * <p>更新产品信息</p>
     * @param productId
     * @return
     */
    @RequestMapping(value = "/product/{productId}",method = RequestMethod.PUT)
    public ResultObject updateProduct(@PathVariable("productId") String productId){
        return ResultObject.builder()
                .code(ResultObjectCode.SUCCESS.getCode())
                .msg(ResultObjectCode.SUCCESS.getMsg())
                .data(null)
                .build();
    }

    /**
     * <p>删除指定产品</p>
     * @param productId
     * @return
     */
    @RequestMapping(value = "/product/{productId}",method = RequestMethod.DELETE)
    public ResultObject deleteProduct(@PathVariable("productId") String productId){
        return ResultObject.builder()
                .code(ResultObjectCode.SUCCESS.getCode())
                .msg(ResultObjectCode.SUCCESS.getMsg())
                .data(null)
                .build();
    }
}
