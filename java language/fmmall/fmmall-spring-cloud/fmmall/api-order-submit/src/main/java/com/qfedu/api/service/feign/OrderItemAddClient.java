package com.qfedu.api.service.feign;

import com.qfedu.api.service.feign.fallback.OrderItemAddClientFallback;
import com.qfedu.fmmall.entity.ShoppingCartVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "order-item-add", fallback = OrderItemAddClientFallback.class)
public interface OrderItemAddClient {
    @PostMapping("/item/add")
    int addOrderItem(@RequestBody List<ShoppingCartVO> lists, @RequestParam("orderId") String orderId);
}
