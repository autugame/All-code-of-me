package com.qfedu.order.service.feign;

import com.qfedu.fmmall.entity.Orders;
import com.qfedu.order.service.feign.fallback.OrderStatusUpdateClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "order-status-update", fallback = OrderStatusUpdateClientFallback.class)
public interface OrderStatusUpdateClient {
    @PutMapping("/order/status/update")
    public int update(@RequestBody Orders order);
}
