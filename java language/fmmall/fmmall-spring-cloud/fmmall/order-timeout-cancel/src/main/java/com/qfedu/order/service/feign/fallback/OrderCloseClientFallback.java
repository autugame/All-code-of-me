package com.qfedu.order.service.feign.fallback;

import com.qfedu.order.service.feign.OrderCloseClient;
import org.springframework.stereotype.Component;

@Component
public class OrderCloseClientFallback implements OrderCloseClient {
    @Override
    public int close(String orderId, int closeType) {
        return 0;
    }
}
