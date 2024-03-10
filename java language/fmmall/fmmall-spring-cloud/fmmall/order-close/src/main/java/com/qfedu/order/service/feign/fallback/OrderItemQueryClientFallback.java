package com.qfedu.order.service.feign.fallback;

import com.qfedu.fmmall.entity.OrderItem;
import com.qfedu.order.service.feign.OrderItemQueryClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemQueryClientFallback implements OrderItemQueryClient {

    @Override
    public List<OrderItem> query(String orderId) {
        return null;
    }
}
