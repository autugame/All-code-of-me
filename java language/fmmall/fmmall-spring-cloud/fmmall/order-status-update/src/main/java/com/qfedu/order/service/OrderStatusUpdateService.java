package com.qfedu.order.service;

import com.qfedu.fmmall.entity.Orders;

public interface OrderStatusUpdateService {
    int updateStatus(Orders order);
}
