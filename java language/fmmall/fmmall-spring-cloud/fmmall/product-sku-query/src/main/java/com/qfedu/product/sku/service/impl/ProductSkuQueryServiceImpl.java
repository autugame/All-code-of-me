package com.qfedu.product.sku.service.impl;

import com.qfedu.fmmall.entity.ProductSku;
import com.qfedu.product.sku.dao.ProductSkuDao;
import com.qfedu.product.sku.service.ProductSkuQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSkuQueryServiceImpl implements ProductSkuQueryService {
    @Autowired
    private ProductSkuDao productSkuDao;
    @Override
    public ProductSku queryProductSku(String skuId) {
        return productSkuDao.selectByPrimaryKey(skuId);
    }
}
