package com.qfedu.order.service.feign.fallback;

import com.qfedu.fmmall.entity.Orders;
import com.qfedu.order.service.feign.OrderStatusUpdateClient;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusUpdateClientFallback implements OrderStatusUpdateClient {
    @Override
    public int update(Orders order) {
        return 0;
    }
}
