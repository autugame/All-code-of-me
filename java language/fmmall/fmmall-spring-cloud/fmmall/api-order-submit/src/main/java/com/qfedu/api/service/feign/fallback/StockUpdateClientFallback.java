package com.qfedu.api.service.feign.fallback;

import com.qfedu.api.service.feign.StockUpdateClient;
import com.qfedu.fmmall.entity.ProductSku;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockUpdateClientFallback implements StockUpdateClient {
    @Override
    public int update(List<ProductSku> skus) {
        return 0;
    }
}
