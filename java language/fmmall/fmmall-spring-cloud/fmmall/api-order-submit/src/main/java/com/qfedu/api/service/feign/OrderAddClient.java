package com.qfedu.api.service.feign;

import com.qfedu.api.service.feign.fallback.OrderAddClientFallback;
import com.qfedu.fmmall.entity.Orders;
import com.qfedu.fmmall.entity.ShoppingCartVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "order-add", fallback = OrderAddClientFallback.class)
public interface OrderAddClient {
    @PostMapping("/order/add")
    List<ShoppingCartVO> add(@RequestBody Orders order, @RequestParam("cids") String cids);
}
