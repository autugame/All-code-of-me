package com.qfedu.fmmall.service.impl;

import com.qfedu.fmmall.dao.UsersMapper;
import com.qfedu.fmmall.entity.Users;
import com.qfedu.fmmall.service.UserService;
import com.qfedu.fmmall.utils.MD5Utils;
import com.qfedu.fmmall.utils.TokenUtils;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    @Transactional
    public ResultVO userRegister(String username, String password) {
        synchronized (this) {
            //1.Query whether a user is registered based on the username.
            Example example = new Example(Users.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("username", username);
            List<Users> users = usersMapper.selectByExample(example);
            if (users.isEmpty()) {
                //2.Save if not registered.
                String md5Pwd = MD5Utils.md5Encode(password);
                Users user = new Users();
                user.setUsername(username);
                user.setPassword(md5Pwd);
                user.setUserImg("img/default.png");
                user.setUserRegtime(new Date());
                user.setUserModtime(new Date());
                int i = usersMapper.insertUseGeneratedKeys(user);
                if (i > 0) {
                    return new ResultVO(ResStatus.OK, "注册成功!", user);
                } else {
                    return new ResultVO(ResStatus.NO, "注册失败!");
                }
            } else {
                return new ResultVO(ResStatus.NO, "用户已被注册!");
            }
        }
    }

    @Override
    public ResultVO checkLogin(String username, String password) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        List<Users> users = usersMapper.selectByExample(example);

        if (users.isEmpty()) {
            return new ResultVO(ResStatus.NO, "登录失败，无此账户!");
        } else {
            String md5Pwd = MD5Utils.md5Encode(password);
            if (md5Pwd.equals(users.get(0).getPassword())) {
                JwtBuilder builder = Jwts.builder();
                String token = TokenUtils.genAccessToken(users.get(0).getUsername());
                return new ResultVO(ResStatus.OK, "登录成功!", users.get(0), token);
            } else {
                return new ResultVO(ResStatus.NO, "登录失败，密码错误!");
            }
        }
    }
}
