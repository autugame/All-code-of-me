package com.qfedu.api.service.feign.fallback;

import com.qfedu.api.service.feign.UserCheckClient;
import com.qfedu.fmmall.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UserCheckClientFallback implements UserCheckClient {
    @Override
    public Users check(String username) {
        return null;
    }
}
