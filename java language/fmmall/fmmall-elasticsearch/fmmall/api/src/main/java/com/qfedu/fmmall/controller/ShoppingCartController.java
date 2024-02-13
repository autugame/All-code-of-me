package com.qfedu.fmmall.controller;

import com.google.gson.Gson;
import com.qfedu.fmmall.entity.ShoppingCart;
import com.qfedu.fmmall.entity.Users;
import com.qfedu.fmmall.service.ShoppingCartService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/shop-cart")
@CrossOrigin
@Api(value = "提供商品信息相关的接口", tags = "购物车管理")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Autowired
//    private Gson gson;

    @PostMapping("/add")
    @ApiOperation(value = "添加购物车接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "ShoppingCart", name = "cart", value = "购物车对象",required = true),
            @ApiImplicitParam(dataType = "String", name = "token", value = "令牌",required = true)
    })
    private ResultVO addShoppingCart(@RequestBody ShoppingCart cart, @RequestHeader("token") String token){
        //String user = stringRedisTemplate.boundValueOps(token).get();
        return shoppingCartService.addShoppingCart(cart);
    }

    @GetMapping("/list")
    @ApiOperation(value = "当前用户购物车列表")
    @ApiImplicitParam(dataType = "Integer", name = "userId", value = "用户id",required = true)
    private ResultVO list(Integer userId, @RequestHeader("token") String token) {
        if (userId != null) {
            return shoppingCartService.listShoppingCartsByUserId(userId);
        }
        return new ResultVO(ResStatus.NO, "The user id is empty.");
    }

    @PutMapping("/update/{cid}/{cnum}")
    @ApiOperation(value = "修改购物车商品数量")
    public ResultVO updateNum(@PathVariable("cid") Integer cartId, @PathVariable("cnum") Integer cartNum, @RequestHeader("token") String token) {
        return shoppingCartService.updateCartNum(cartId, cartNum);
    }

    @DeleteMapping("/delete/{cid}")
    @ApiOperation(value = "删除购物车商品")
    public ResultVO deleteCart(@PathVariable("cid") Integer cartId, @RequestHeader("token") String token) {
        return shoppingCartService.deleteShoppingCartByCartId(cartId);
    }

    @GetMapping("/listbycids")
    @ApiOperation(value = "id字符串查询购物车")
    @ApiImplicitParam(dataType = "String", name = "cids", value = "购物车id字符串",required = true)
    private ResultVO listByCids(String cids, @RequestHeader("token") String token) {
        if (cids != null) {
            return shoppingCartService.listShoppingCartByCids(cids);
        }
        return new ResultVO(ResStatus.NO, "The cart id is empty.");
    }
}
