package com.qfedu.order.service.feign.fallback;

import com.qfedu.fmmall.entity.ProductSku;
import com.qfedu.order.service.feign.ProductSkuQueryClient;
import org.springframework.stereotype.Repository;

@Repository
public class ProductSkuQueryClientFallback implements ProductSkuQueryClient {

    @Override
    public ProductSku query(String skuId) {
        return null;
    }
}
