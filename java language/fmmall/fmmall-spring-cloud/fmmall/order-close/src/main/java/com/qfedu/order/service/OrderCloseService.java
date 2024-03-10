package com.qfedu.order.service;

public interface OrderCloseService {
    int closeOrder(String orderId, int closeType);
}
