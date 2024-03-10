package com.qfedu.api.service.feign.fallback;

import com.qfedu.api.service.feign.OrderItemAddClient;
import com.qfedu.fmmall.entity.ShoppingCartVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemAddClientFallback implements OrderItemAddClient {

    @Override
    public int addOrderItem(List<ShoppingCartVO> lists, String orderId) {
        return 0;
    }
}
