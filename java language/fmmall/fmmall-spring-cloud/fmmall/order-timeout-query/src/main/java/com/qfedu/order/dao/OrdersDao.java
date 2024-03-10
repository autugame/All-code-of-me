package com.qfedu.order.dao;

import com.qfedu.fmmall.entity.Orders;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Repository
public interface OrdersDao extends Mapper<Orders>, MySqlMapper<Orders> {
}
