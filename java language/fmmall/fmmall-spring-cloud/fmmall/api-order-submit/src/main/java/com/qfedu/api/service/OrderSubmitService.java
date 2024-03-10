package com.qfedu.api.service;

import com.qfedu.fmmall.entity.Orders;

import java.util.Map;

public interface OrderSubmitService {
    Map<String, String> addOrder(String cids, Orders order);
}
