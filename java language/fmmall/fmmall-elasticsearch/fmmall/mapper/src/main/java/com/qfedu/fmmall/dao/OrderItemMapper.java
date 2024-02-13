package com.qfedu.fmmall.dao;

import com.qfedu.fmmall.entity.OrderItem;
import com.qfedu.fmmall.general.GeneralDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemMapper extends GeneralDao<OrderItem> {
    List<OrderItem> listOrderItemsByOrderId(String orderId);
}