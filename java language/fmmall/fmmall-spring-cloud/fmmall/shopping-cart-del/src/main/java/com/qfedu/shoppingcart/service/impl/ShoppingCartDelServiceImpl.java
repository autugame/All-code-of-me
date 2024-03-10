package com.qfedu.shoppingcart.service.impl;

import com.qfedu.shoppingcart.dao.ShoppingCartDao;
import com.qfedu.shoppingcart.service.ShoppingCartDelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartDelServiceImpl implements ShoppingCartDelService {
    @Autowired
    private ShoppingCartDao shoppingCartDao;
    @Override
    public int delShoppingCart(String cids) {
        int i = 1;
        String[] arr = cids.split(",");
        for (String s : arr) {
            int cid = Integer.parseInt(s);
            int j = shoppingCartDao.deleteByPrimaryKey(cid);
            i *= j;
        }
        return i;
    }
}
