package com.qfedu.order.controller;

import com.qfedu.fmmall.entity.OrderItem;
import com.qfedu.order.service.OrderItemQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderItemQueryController {
    @Autowired
    private OrderItemQueryService orderItemQueryService;
    @GetMapping("/order/item/query")
    public List<OrderItem> query(String orderId) {
        return orderItemQueryService.queryOrderItem(orderId);
    }
}
