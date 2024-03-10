package com.qfedu.user.dao;

import com.qfedu.fmmall.entity.Users;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Repository
public interface UserDao extends Mapper<Users>, MySqlMapper<Users> {
}
