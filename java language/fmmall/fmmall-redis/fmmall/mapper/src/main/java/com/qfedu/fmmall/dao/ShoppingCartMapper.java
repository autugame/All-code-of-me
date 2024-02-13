package com.qfedu.fmmall.dao;

import com.qfedu.fmmall.entity.ShoppingCart;
import com.qfedu.fmmall.entity.ShoppingCartVO;
import com.qfedu.fmmall.general.GeneralDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartMapper extends GeneralDao<ShoppingCart> {
    public List<ShoppingCartVO> selectShoppingCartByUserId(int userId);
    public int updateCartNumByCartId(@Param("cartNum") int cartNum, @Param("cartId") int cartId);
    public List<ShoppingCartVO> selectShoppingCartByCids(List<Integer> cids);
}