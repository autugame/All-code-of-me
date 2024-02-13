package com.qfedu.fmmall.service.impl;

import com.qfedu.fmmall.dao.ShoppingCartMapper;
import com.qfedu.fmmall.entity.ShoppingCart;
import com.qfedu.fmmall.service.ShoppingCartService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public ResultVO addShoppingCart(ShoppingCart cart) {
        cart.setCartTime(sdf.format(new Date()));
        int i = shoppingCartMapper.insert(cart);
        if (i > 0) {
            return new ResultVO(ResStatus.OK, "success");
        } else {
            return new ResultVO(ResStatus.NO, "success");
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResultVO listShoppingCartsByUserId(int userId) {
        return new ResultVO(ResStatus.OK, "success", shoppingCartMapper.selectShoppingCartByUserId(userId));
    }

    @Override
    public ResultVO updateCartNum(int cartId, int cartNum) {
        int i = shoppingCartMapper.updateCartNumByCartId(cartNum, cartId);
        if (i > 0) {
            return new ResultVO(ResStatus.OK, "update success.");
        } else {
            return new ResultVO(ResStatus.NO, "update fail.");
        }
    }

    @Override
    public ResultVO deleteShoppingCartByCartId(int cartId) {
        int i = shoppingCartMapper.deleteByPrimaryKey(cartId);
        if (i > 0) {
            return new ResultVO(ResStatus.OK, "delete success.");
        } else {
            return new ResultVO(ResStatus.NO, "delete fail.");
        }
    }

    @Override
    public ResultVO listShoppingCartByCids(String cids) {
        String[] arr = cids.split(",");
        List<Integer> cartIds = new ArrayList<>();
        for (String str : arr) {
            cartIds.add(Integer.parseInt(str));
        }
        return new ResultVO(ResStatus.OK, "success", shoppingCartMapper.selectShoppingCartByCids(cartIds));
    }
}
