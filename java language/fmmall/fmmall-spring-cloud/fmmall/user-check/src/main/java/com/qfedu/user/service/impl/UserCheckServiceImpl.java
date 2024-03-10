package com.qfedu.user.service.impl;

import com.qfedu.fmmall.entity.Users;
import com.qfedu.user.dao.UserDao;
import com.qfedu.user.service.UserCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserCheckServiceImpl implements UserCheckService {
    @Autowired
    private UserDao userDao;

    @Override
    public Users queryUser(String username) {
        Example example = new Example(Users.class);
        example.createCriteria()
                .andEqualTo("username", username);
        List<Users> users = userDao.selectByExample(example);
        if (!users.isEmpty()) {
            return users.get(0);
        } else {
            return null;
        }
    }
}
