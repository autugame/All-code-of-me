package com.qfedu.fmmall.service;

import com.qfedu.fmmall.entity.UserAddr;
import com.qfedu.fmmall.vo.ResultVO;

import java.util.List;

public interface UserAddrService {
    public ResultVO listAddrsByUid(int userId);
}
