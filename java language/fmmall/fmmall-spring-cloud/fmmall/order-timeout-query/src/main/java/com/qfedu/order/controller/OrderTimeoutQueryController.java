package com.qfedu.order.controller;

import com.qfedu.fmmall.entity.Orders;
import com.qfedu.order.service.OrderTimeoutQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderTimeoutQueryController {
    @Autowired
    private OrderTimeoutQueryService orderTimeoutQueryService;

    @GetMapping("/order/query/timeout")
    public List<Orders> query() {
        //Inquire and return unpaid orders.
        return orderTimeoutQueryService.listOrders();
    }
}
