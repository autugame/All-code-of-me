package com.qfedu.order.service.feign;

import com.qfedu.fmmall.entity.OrderItem;
import com.qfedu.order.service.feign.fallback.OrderItemQueryClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "order-item-query", fallback = OrderItemQueryClientFallback.class)
public interface OrderItemQueryClient {
    @GetMapping("/order/item/query")
    public List<OrderItem> query(@RequestParam("orderId") String orderId);
}
