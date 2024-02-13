package com.qfedu.fmmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 表名：shopping_cart
 * 表注释：购物车
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "shopping_cart")
public class ShoppingCartVO {
    /**
     * 主键
     */
    @Id
    @Column(name = "cart_id")
    private Integer cartId;

    /**
     * 商品ID
     */
    @Column(name = "product_id")
    private String productId;

    /**
     * skuID
     */
    @Column(name = "sku_id")
    private String skuId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 购物车商品数量
     */
    @Column(name = "cart_num")
    private String cartNum;

    /**
     * 添加购物车时间
     */
    @Column(name = "cart_time")
    private String cartTime;

    /**
     * 添加购物车时商品价格
     */
    @Column(name = "product_price")
    private BigDecimal productPrice;

    /**
     * 选择的套餐的属性
     */
    @Column(name = "sku_props")
    private String skuProps;


    private String productName;
    private String productImg;
    private double originalPrice;
    private double sellPrice;
    private String skuName;
    private int skuStock;
}