package com.qfedu.fmmall.service.impl;

import com.qfedu.fmmall.dao.UserAddrMapper;
import com.qfedu.fmmall.entity.UserAddr;
import com.qfedu.fmmall.service.UserAddrService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;


@Service
public class UserAddrServiceImpl implements UserAddrService {
    @Autowired
    private UserAddrMapper userAddrMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResultVO listAddrsByUid(int userId) {
        Example example = new Example(UserAddr.class);
        example.createCriteria()
                .andEqualTo("userId", userId)
                .andEqualTo("status", 1);
        return new ResultVO(ResStatus.OK, "success", userAddrMapper.selectByExample(example));
    }
}
