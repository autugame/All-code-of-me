package com.qfedu.order.controller;

import com.qfedu.fmmall.entity.ShoppingCartVO;
import com.qfedu.order.service.OrderItemAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderItemAddController {
    @Autowired
    private OrderItemAddService orderItemAddService;
    @PostMapping("/item/add")
    public int addOrderItem(@RequestBody List<ShoppingCartVO> lists, String orderId) {
        return orderItemAddService.add(lists, orderId);
    }
}
