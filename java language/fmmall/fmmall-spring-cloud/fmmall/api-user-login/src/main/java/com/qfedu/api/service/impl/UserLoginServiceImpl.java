package com.qfedu.api.service.impl;

import com.google.gson.Gson;
import com.qfedu.api.service.UserLoginService;
import com.qfedu.api.service.feign.UserCheckClient;
import com.qfedu.fmmall.entity.Users;
import com.qfedu.fmmall.utils.MD5Utils;
import com.qfedu.fmmall.utils.TokenUtils;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserCheckClient userCheckClient;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private Gson gson;
    @Override
    public ResultVO checkLogin(String username, String password) {
        //Call the user-check service to query user information based on the username.
        Users user = userCheckClient.check(username);

        //Verify user information.
        if (user == null) {
            return new ResultVO(ResStatus.NO, "login failure!");
        } else {
            String md5Pwd = MD5Utils.md5Encode(password);
            if (md5Pwd.equals(user.getPassword())) {
                JwtBuilder builder = Jwts.builder();
                String token = TokenUtils.genAccessToken(user.getUsername());

                //When the user logs in successfully, then the information store in redis with the token as the key.
                stringRedisTemplate.boundValueOps(token).set(gson.toJson(user), 30, TimeUnit.MINUTES);
                return new ResultVO(ResStatus.OK, "登录成功!", user, token);
            } else {
                return new ResultVO(ResStatus.NO, "login failure!");
            }
        }
    }
}
