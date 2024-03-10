package com.qfedu.api.service;

import com.qfedu.fmmall.vo.ResultVO;

public interface UserRegisterService {
    public ResultVO register(String username, String password);
}
