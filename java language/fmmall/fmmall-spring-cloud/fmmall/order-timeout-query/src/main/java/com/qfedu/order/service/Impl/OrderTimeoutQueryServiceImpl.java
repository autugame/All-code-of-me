package com.qfedu.order.service.Impl;

import com.qfedu.fmmall.entity.Orders;
import com.qfedu.order.dao.OrdersDao;
import com.qfedu.order.service.OrderTimeoutQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class OrderTimeoutQueryServiceImpl implements OrderTimeoutQueryService {
    @Autowired
    private OrdersDao ordersDao;
    @Override
    public List<Orders> listOrders() {
        //Check orders that have not been paid for more than 30 minutes.
        Example example = new Example(Orders.class);
        Date time = new Date(System.currentTimeMillis() - 30*60*1000);
        example.createCriteria()
                .andEqualTo("status", "1")
                .andLessThan("createTime", time);
        return ordersDao.selectByExample(example);
    }
}
