package com.qfedu.fmmall.service;

import com.qfedu.fmmall.vo.ResultVO;

public interface UserService {
    //User register.
    public ResultVO userRegister(String username, String password);
    public ResultVO checkLogin(String username, String password);
}
