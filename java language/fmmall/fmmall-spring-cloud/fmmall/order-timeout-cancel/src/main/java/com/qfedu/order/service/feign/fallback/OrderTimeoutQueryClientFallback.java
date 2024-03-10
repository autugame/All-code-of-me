package com.qfedu.order.service.feign.fallback;

import com.qfedu.fmmall.entity.Orders;
import com.qfedu.order.service.feign.OrderTimeoutQueryClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderTimeoutQueryClientFallback implements OrderTimeoutQueryClient {

    @Override
    public List<Orders> query() {
        return new ArrayList<>();
    }
}
