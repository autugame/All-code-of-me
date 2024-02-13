package com.qfedu.fmmall.controller;

import com.qfedu.fmmall.entity.Users;
import com.qfedu.fmmall.service.UserService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Api(value = "提供用户的登录和注册接口", tags = "用户管理")
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "username", value = "用户登录账号", required = true),
            @ApiImplicitParam(dataType = "string", name = "password", value = "用户登录密码", required = true)
    })
    @GetMapping("/login")
    public ResultVO login(String username, String password){
        return userService.checkLogin(username, password);
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "Users",name = "users", value = "用户注册对象",required = true),
    })
    public ResultVO register(@RequestBody Users users) {
        return userService.userRegister(users.getUsername(), users.getPassword());
    }

    @ApiOperation(value = "校验token是否过期")
    @GetMapping("/check")
    public ResultVO userTokenCheck(@RequestHeader("token") String token) {
        return new ResultVO(ResStatus.OK, "success");
    }
}
