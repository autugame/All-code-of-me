package com.qfedu.api.service.feign.fallback;

import com.qfedu.api.service.feign.ShoppingCartDelClient;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartDelClientFallback implements ShoppingCartDelClient {

    @Override
    public int delete(String cids) {
        return 0;
    }
}
