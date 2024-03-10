package com.qfedu.order.service.impl;

import com.qfedu.fmmall.entity.OrderItem;
import com.qfedu.order.dao.OrderItemDao;
import com.qfedu.order.service.OrderItemQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OrderItemQueryServiceImpl implements OrderItemQueryService {
    @Autowired
    private OrderItemDao orderItemDao;
    @Override
    public List<OrderItem> queryOrderItem(String orderId) {
        Example example = new Example(OrderItem.class);
        example.createCriteria()
                .andEqualTo("orderId", orderId);
        List<OrderItem> orderItems = orderItemDao.selectByExample(example);
        return null;
    }
}
