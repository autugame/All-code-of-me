package com.qfedu.order.service.impl;

import com.qfedu.fmmall.entity.OrderItem;
import com.qfedu.fmmall.entity.ShoppingCartVO;
import com.qfedu.order.dao.OrderItemDao;
import com.qfedu.order.service.OrderItemAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderItemAddServiceImpl implements OrderItemAddService {

    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    public int add(List<ShoppingCartVO> lists, String orderId) {
        int i = 1;
        //Generating product snapshot.
        for (ShoppingCartVO sc : lists) {
            String itemId = System.currentTimeMillis() + "" + (new Random().nextInt(89999) + 10000);
            int cnum = Integer.parseInt(sc.getCartNum());
            OrderItem orderItem = new OrderItem(itemId, orderId, sc.getProductId(), sc.getProductName(), sc.getProductImg(), sc.getSkuId(), sc.getSkuName(), BigDecimal.valueOf(sc.getSellPrice()), cnum, BigDecimal.valueOf(sc.getSellPrice() * cnum), new Date(), new Date(), 0);
            int j = orderItemDao.insert(orderItem);
            i = i * j;
        }
        return i;
    }
}
