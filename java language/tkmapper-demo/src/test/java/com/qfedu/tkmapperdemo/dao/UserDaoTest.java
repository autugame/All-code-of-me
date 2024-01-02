package com.qfedu.tkmapperdemo.dao;

import com.qfedu.tkmapperdemo.TkmapperDemoApplication;
import com.qfedu.tkmapperdemo.beans.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TkmapperDemoApplication.class)
class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void test(){
        User user = new User();
        user.setUsername("aaa");
        user.setPassword("1111");
        user.setUserImg("/img/default.png");
        user.setUserRegtime(new Date());
        user.setUserModtime(new Date());
        int i = userDao.insert(user);
        System.out.println(i);
    }
}