package com.qfedu.api.service;

import com.qfedu.fmmall.vo.ResultVO;

public interface UserLoginService {
    public ResultVO checkLogin(String username, String password);
}
