package com.qfedu.api.service.feign;

import com.qfedu.api.service.feign.fallback.ShoppingCartDelClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "shopping-cart-del", fallback = ShoppingCartDelClientFallback.class)
public interface ShoppingCartDelClient {
    @DeleteMapping("/shopping/del")
    int delete(@RequestParam("cids") String cids);
}
