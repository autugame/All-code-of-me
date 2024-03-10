package com.qfedu.shoppingcart.dao;

import com.qfedu.fmmall.entity.ShoppingCart;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Repository
public interface ShoppingCartDao extends Mapper<ShoppingCart>, MySqlMapper<ShoppingCart> {
}
