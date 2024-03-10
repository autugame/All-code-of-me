package com.qfedu.stock.service.impl;

import com.qfedu.fmmall.entity.ShoppingCartVO;
import com.qfedu.stock.dao.ShoppingCartDao;
import com.qfedu.stock.service.StockQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockQueryServiceImpl implements StockQueryService {
    @Autowired
    private ShoppingCartDao shoppingCartDao;
    @Override
    public List<ShoppingCartVO> selectShoppingCartByCids(String cids) {
        String[] arr = cids.split(",");
        List<Integer> cidsList = new ArrayList<>();
        for (String s : arr) {
            cidsList.add(Integer.parseInt(s));
        }
        return shoppingCartDao.selectShoppingCartByCids(cidsList);
    }
}
