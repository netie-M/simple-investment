package com.investment.product.service.impl;

import com.investment.product.entity.Product;
import com.investment.product.repository.ProductRepository;
import com.investment.product.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    //key -> product::product_A101001011
    @Cacheable(value = "product",key = "'product_'+#productNo")
    @Override
    public Product queryProduct(String productNo) {
        log.info("select from db");
        return productRepository.findByProductNo(productNo);
    }
    /**
     * 售罄后需要完成的操作：
     * 1. 更新产品状态在售->售罄
     * 2. 通知选取最新的精品产品(可选)
     * 3. 通知选取同种类最新的产品创建->在售
     */
    /**
     * 下单后操作：
     * 1. 检查重复请求
     * 11.检查订单是否重复
     * 12.检查交易密码
     * 2. 保存订单
     * 3. 减库存
     * 4. 减积分
     * 5. 支付（第三方接口）
     * 6. 恢复库存（可选）
     * 7. 恢复积分（可选）
     * 11. 通知合同系统记录合同
     * 12. 通知产品系统支付成功记录明细，产品售罄检查，通知售罄
     * 13. 通知积分系统支付成功发放奖励
     */

    /**
     * 订单：
     * 检查重复请求
     * 检查订单是否重复
     * 检查积分是否充足
     * 保存订单（含支付编号）
     * 减库存（产品）
     * 减积分（积分）
     *
     * 支付：
     * 检查重复请求
     * 检查支付是否重复
     * 检查订单状态
     * 检查交易密码，支付系统？用户系统？
     * 获取用户信息，订单信息
     * 支付（第三方接口）
     * 通知各个系统支付结果
     *      合同系统
     *      积分系统
     *      产品系统
     *      订单系统
     */
}
