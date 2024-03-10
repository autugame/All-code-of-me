package com.qfedu.order.service;

import com.qfedu.fmmall.entity.OrderItem;

import java.util.List;

public interface OrderItemQueryService {
    List<OrderItem> queryOrderItem(String orderId);
}
