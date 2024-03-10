package com.qfedu.api.controller;

import com.qfedu.api.service.UserLoginService;
import com.qfedu.fmmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;

    @GetMapping("/user/login")
    public ResultVO login(String username, String password){
        return userLoginService.checkLogin(username, password);
    }

}
