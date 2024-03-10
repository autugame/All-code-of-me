package com.qfedu.order.service;

import com.qfedu.fmmall.entity.ShoppingCartVO;

import java.util.List;

public interface OrderItemAddService {
    int add(List<ShoppingCartVO> lists, String orderId);
}
