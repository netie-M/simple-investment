package com.investment.product.manage;

import com.investment.product.dto.ProductDTO;
import com.investment.product.entity.Product;
import com.investment.product.entity.ProductStatus;
import com.investment.product.enums.SaleStatus;
import com.investment.product.repository.ProductRepository;
import com.investment.product.repository.ProductStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Component
@Slf4j
public class ProductManage {

    @Autowired
    private ProductStatusRepository productStatusRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void saleout(String productNo){
        Product product = productRepository.findByProductNo(productNo);
        ProductStatus productStatus = productStatusRepository.findByProductNo(productNo);
        log.info("step：产品售罄检查，productNo = {},productName = {}",product.getProductNo(),product.getProductName());
        BigDecimal minInvestmentAmount = product.getMinInvestmentAmount();
        BigDecimal remainAmount = productStatus.getRemainAmount();
        if (remainAmount.compareTo(minInvestmentAmount) < 0){
            String newSaleStatus = SaleStatus.SALE_STATUS_OUT.getCode();
            log.info("step：产品状态变更，productNo = {},oldSaleStatus = {}",product.getProductNo(),product.getSaleStatus(),newSaleStatus);
            product.setSaleStatus(newSaleStatus);
            productRepository.save(product);
        }
    }

    public void saleStatusChange(String productNo,String newSaleStatus){
        Product product = productRepository.findByProductNo(productNo);
        log.info("step：产品状态变更，productNo = {},oldSaleStatus = {}",product.getProductNo(),product.getSaleStatus(),newSaleStatus);
        product.setSaleStatus(newSaleStatus);
        productRepository.save(product);
    }

    @Transactional
    public void createProduct(ProductDTO productDTO){
        productDTO.setId(null);
        log.info("step：新建产品，productNo = {},productName = {}",productDTO.getProductNo(),productDTO.getProductName());
        Product product = new Product();
        BeanCopier copierProduct = BeanCopier.create(ProductDTO.class, Product.class, false);
        copierProduct.copy(productDTO,product,null);

        ProductStatus productStatus = new ProductStatus();
        BeanCopier copierProductStatus = BeanCopier.create(ProductDTO.class, ProductStatus.class, false);
        copierProductStatus.copy(productDTO,productStatus,null);

        productRepository.save(product);
        productStatusRepository.save(productStatus);
    }

}
