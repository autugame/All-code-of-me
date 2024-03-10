package com.qfedu.api.service.feign;

import com.qfedu.fmmall.entity.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("user-save")
public interface UserSaveClient {
    @PostMapping("/user/save")
    public int save(@RequestBody Users user);
}
