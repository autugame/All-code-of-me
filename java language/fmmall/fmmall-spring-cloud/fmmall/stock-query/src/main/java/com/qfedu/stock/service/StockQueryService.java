package com.qfedu.stock.service;

import com.qfedu.fmmall.entity.ShoppingCartVO;

import java.util.List;

public interface StockQueryService {
    List<ShoppingCartVO> selectShoppingCartByCids(String cids);
}
