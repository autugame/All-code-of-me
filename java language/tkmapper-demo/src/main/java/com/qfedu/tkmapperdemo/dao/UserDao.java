package com.qfedu.tkmapperdemo.dao;

import com.qfedu.tkmapperdemo.beans.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Repository
public interface UserDao extends Mapper<User>, MySqlMapper<User> {
}
