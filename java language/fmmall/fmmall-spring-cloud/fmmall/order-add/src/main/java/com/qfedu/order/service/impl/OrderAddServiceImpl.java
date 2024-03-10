package com.qfedu.order.service.impl;

import com.qfedu.fmmall.entity.Orders;
import com.qfedu.fmmall.entity.ShoppingCartVO;
import com.qfedu.order.dao.OrdersDao;
import com.qfedu.order.service.OrderAddService;
import com.qfedu.order.service.feign.StockQueryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderAddServiceImpl implements OrderAddService {
    @Autowired
    private StockQueryClient stockQueryClient;

    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<ShoppingCartVO> add(Orders order, String cids) {
        //Verify the inventory.
        //Call the stock-query service.
        List<ShoppingCartVO> lists = stockQueryClient.query(cids);
        if (lists != null && !lists.isEmpty()) {
            boolean flag = true;
            StringBuilder untitled = new StringBuilder();
            for (int i = 0; i < lists.size(); i++) {
                ShoppingCartVO sc = lists.get(i);
                if (Integer.parseInt(sc.getCartNum()) > sc.getSkuStock()) {
                    flag = false;
                    break;
                }
                if (i < lists.size() - 1) {
                    untitled.append(sc.getProductName()).append(",");
                } else {
                    untitled.append(sc.getProductName());
                }
            }
            if (flag) {
                //Sufficient inventory.
                //Add order information.
                order.setUntitled(untitled.toString());
                order.setCreateTime(new Date());
                order.setStatus("1");

                //Generating order number.
//                String orderId = UUID.randomUUID().toString().replace("-", "");
//                order.setOrderId(orderId);

                //Add order.
                int i = ordersDao.insert(order);
                if (i > 0) {
                    return lists;
                }
            }
        }
        return null;
    }
}
