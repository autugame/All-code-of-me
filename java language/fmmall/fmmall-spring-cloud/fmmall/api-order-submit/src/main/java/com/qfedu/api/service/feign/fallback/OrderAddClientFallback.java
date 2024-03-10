package com.qfedu.api.service.feign.fallback;

import com.qfedu.api.service.feign.OrderAddClient;
import com.qfedu.fmmall.entity.Orders;
import com.qfedu.fmmall.entity.ShoppingCartVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderAddClientFallback implements OrderAddClient {

    @Override
    public List<ShoppingCartVO> add(Orders order, String cids) {
        return null;
    }
}
