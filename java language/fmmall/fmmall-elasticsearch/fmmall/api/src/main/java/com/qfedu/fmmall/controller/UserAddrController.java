package com.qfedu.fmmall.controller;

import com.qfedu.fmmall.service.UserAddrService;
import com.qfedu.fmmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-addr")
@CrossOrigin
@Api(value = "提供用户地址相关的接口", tags = "用户地址管理")
public class UserAddrController {
    @Autowired
    private UserAddrService userAddrService;

    @GetMapping("/list")
    @ApiOperation(value = "查询用户地址")
    @ApiImplicitParam(dataType = "Integer", name = "userId", value = "用户id", required = true)
    public ResultVO listAddr(Integer userId, @RequestHeader("token") String token) {
        return userAddrService.listAddrsByUid(userId);
    }
}
