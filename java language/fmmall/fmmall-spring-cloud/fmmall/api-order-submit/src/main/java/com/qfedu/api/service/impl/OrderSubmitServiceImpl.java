package com.qfedu.api.service.impl;

import com.qfedu.api.service.OrderSubmitService;
import com.qfedu.api.service.feign.OrderAddClient;
import com.qfedu.api.service.feign.OrderItemAddClient;
import com.qfedu.api.service.feign.ShoppingCartDelClient;
import com.qfedu.api.service.feign.StockUpdateClient;
import com.qfedu.fmmall.entity.Orders;
import com.qfedu.fmmall.entity.ProductSku;
import com.qfedu.fmmall.entity.ShoppingCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderSubmitServiceImpl implements OrderSubmitService {
    @Autowired
    private OrderAddClient orderAddClient;

    @Autowired
    private OrderItemAddClient orderItemAddClient;

    @Autowired
    private StockUpdateClient stockUpdateClient;

    @Autowired
    private ShoppingCartDelClient shoppingCartDelClient;
    @Override
    public Map<String, String> addOrder(String cids, Orders order) {
        Map<String, String> map = null;
        //Add an order.
        String orderId = UUID.randomUUID().toString().replace("-", "");
        order.setOrderId(orderId);
        List<ShoppingCartVO> carts = orderAddClient.add(order, cids);
        //Add a snapshot of the order.
        if (carts != null) {
            int i = orderItemAddClient.addOrderItem(carts, orderId);
            if (i > 0) {
                List<ProductSku> skus = new ArrayList<>();
                for (ShoppingCartVO sc : carts) {
                    int Inventory = sc.getSkuStock() - Integer.parseInt(sc.getCartNum());
                    ProductSku productSku = new ProductSku();
                    productSku.setSkuId(sc.getSkuId());
                    productSku.setStock(Inventory);
                    skus.add(productSku);
                }
                //Modify inventory.
                int j = stockUpdateClient.update(skus);
                if (j > 0) {
                    //Delete the cart.
                    int k = shoppingCartDelClient.delete(cids);
                    if (k > 0) {
                        map = new HashMap<>();

                        StringBuilder untitled = new StringBuilder();
                        for (int index = 0; index < carts.size(); index++) {
                            if (i < carts.size() - 1) {
                                untitled.append(carts.get(index).getSkuName()).append(",");
                            } else {
                                untitled.append(carts.get(index).getSkuName());
                            }
                        }
                        map.put("orderId", orderId);
                        map.put("productName", untitled.toString());
                    }
                }
            }
        }
        return map;
    }
}
