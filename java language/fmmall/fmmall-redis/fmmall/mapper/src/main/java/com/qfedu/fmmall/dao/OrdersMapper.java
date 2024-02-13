package com.qfedu.fmmall.dao;

import com.qfedu.fmmall.entity.Orders;
import com.qfedu.fmmall.entity.OrdersVO;
import com.qfedu.fmmall.general.GeneralDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersMapper extends GeneralDao<Orders> {
    public List<OrdersVO> selectOrders(@Param("userId") String userId,
                                       @Param("status") String status,
                                       @Param("start") int start,
                                       @Param("limit") int limit);
}