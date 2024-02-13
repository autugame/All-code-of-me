package com.qfedu.fmmall.dao;

import com.qfedu.fmmall.entity.ProductSku;
import com.qfedu.fmmall.general.GeneralDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSkuMapper extends GeneralDao<ProductSku> {
    /**
     * Find the item with the lowest current price based on the product id.
     * @param productId
     * @return
     */
    List<ProductSku> selectLowestPriceByProductId(String productId);
}