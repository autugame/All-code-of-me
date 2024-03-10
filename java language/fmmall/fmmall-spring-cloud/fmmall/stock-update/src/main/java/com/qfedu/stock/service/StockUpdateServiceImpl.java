package com.qfedu.stock.service;

import com.qfedu.fmmall.entity.ProductSku;
import com.qfedu.stock.dao.ProductSkuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockUpdateServiceImpl implements StockUpdateService {
    @Autowired
    private ProductSkuDao productSkuDao;

    @Override
    public int updateStock(List<ProductSku> skus) {
        int i = 1;
        for (ProductSku sku : skus) {
            int j = productSkuDao.updateByPrimaryKeySelective(sku);
            i *= j;
        }
        return i;
    }
}
