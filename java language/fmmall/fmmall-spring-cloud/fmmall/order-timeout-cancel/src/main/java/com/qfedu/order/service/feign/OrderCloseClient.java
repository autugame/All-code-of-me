package com.qfedu.order.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("order-close")
public interface OrderCloseClient {
    @GetMapping("/order/close")
    public int close(@RequestParam("orderId") String orderId, @RequestParam("closeType") int closeType);
}
