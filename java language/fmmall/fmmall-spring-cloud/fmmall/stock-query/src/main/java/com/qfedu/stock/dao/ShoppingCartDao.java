package com.qfedu.stock.dao;

import com.qfedu.fmmall.entity.ShoppingCart;
import com.qfedu.fmmall.entity.ShoppingCartVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

@Repository
public interface ShoppingCartDao extends Mapper<ShoppingCart>, MySqlMapper<ShoppingCart> {
    List<ShoppingCartVO> selectShoppingCartByCids(List<Integer> list);
}
