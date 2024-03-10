package com.qfedu.user.service.impl;

import com.qfedu.fmmall.entity.Users;
import com.qfedu.user.dao.UserDao;
import com.qfedu.user.service.UserSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSaveServiceImpl implements UserSaveService {
    @Autowired
    private UserDao userDao;
    @Override
    public int saveUser(Users user) {
        int i = userDao.insertUseGeneratedKeys(user);
        return i;
    }
}
