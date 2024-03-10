package com.qfedu.order.service.feign;

import com.qfedu.fmmall.entity.Orders;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("order-timeout-query")
public interface OrderTimeoutQueryClient {
    @GetMapping("/order/query/timeout")
    public List<Orders> query();
}
