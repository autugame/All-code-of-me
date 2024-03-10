package com.qfedu.order.service.impl;

import com.qfedu.fmmall.entity.OrderItem;
import com.qfedu.fmmall.entity.Orders;
import com.qfedu.fmmall.entity.ProductSku;
import com.qfedu.order.service.OrderCloseService;
import com.qfedu.order.service.feign.OrderItemQueryClient;
import com.qfedu.order.service.feign.OrderStatusUpdateClient;
import com.qfedu.order.service.feign.ProductSkuQueryClient;
import com.qfedu.order.service.feign.StockUpdateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderCloseServiceImpl implements OrderCloseService {
    @Autowired
    private OrderStatusUpdateClient orderStatusUpdateClient;
    @Autowired
    private OrderItemQueryClient orderItemQueryClient;
    @Autowired
    private ProductSkuQueryClient productSkuQueryClient;
    @Autowired
    private StockUpdateClient stockUpdateClient;

    @Override
    public int closeOrder(String orderId, int closeType) {
        //Call the order-status-update service.
        Orders order = new Orders();
        order.setOrderId(orderId);
        order.setStatus("6");
        order.setCloseType(closeType);
        int i = orderStatusUpdateClient.update(order);
        if (i > 0) {
            //Call the order-item-query service.
            List<OrderItem> orderItems = orderItemQueryClient.query(orderId);
            if (orderItems != null && !orderItems.isEmpty()) {
                List<ProductSku> skus = new ArrayList<>();
                //Call the stock-update service.
                for (OrderItem item : orderItems) {
                    String skuId = item.getSkuId();
                    //Query the information of the current package based on the sku id.
                    //Call the product-sku-query service.
                    ProductSku sku = productSkuQueryClient.query(skuId);
                    sku.setStock(sku.getStock() + item.getBuyCounts());
                    skus.add(sku);
                }
                //Call the stock-update service.
                return stockUpdateClient.update(skus);
            }
        }
        return 0;
    }
}
