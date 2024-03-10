package com.qfedu.api.controller;

import com.qfedu.api.service.UserRegisterService;
import com.qfedu.fmmall.entity.Users;
import com.qfedu.fmmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserRegisterController {
    @Autowired
    private UserRegisterService userRegisterService;

    @PostMapping("/register")
    public ResultVO register(@RequestBody Users user) {
        return userRegisterService.register(user.getUsername(), user.getPassword());
    }
}
