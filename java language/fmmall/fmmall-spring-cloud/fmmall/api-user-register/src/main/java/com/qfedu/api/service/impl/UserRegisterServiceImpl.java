package com.qfedu.api.service.impl;

import com.qfedu.api.service.UserRegisterService;
import com.qfedu.api.service.feign.UserCheckClient;
import com.qfedu.api.service.feign.UserSaveClient;
import com.qfedu.fmmall.entity.Users;
import com.qfedu.fmmall.utils.MD5Utils;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {
    @Autowired
    private UserCheckClient userCheckClient;

    @Autowired
    private UserSaveClient userSaveClient;

    @Override
    public ResultVO register(String username, String password) {
        synchronized (this) {
            //Call the user-check service
            //1.Query whether a user is registered based on the username.
            Users user = userCheckClient.check(username);

            if (user == null) {
                //2.Save if not registered.
                String md5Pwd = MD5Utils.md5Encode(password);
                user = new Users();
                user.setUsername(username);
                user.setPassword(md5Pwd);
                user.setUserImg("img/default.png");
                user.setUserRegtime(new Date());
                user.setUserModtime(new Date());
                //Call the user-save service to save user information.
                int i = userSaveClient.save(user);
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
}
