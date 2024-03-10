package com.qfedu.order.service;

import com.github.wxpay.sdk.WXPay;
import com.qfedu.fmmall.entity.Orders;
import com.qfedu.order.service.config.PayConfig;
import com.qfedu.order.service.feign.OrderCloseClient;
import com.qfedu.order.service.feign.OrderStatusUpdateClient;
import com.qfedu.order.service.feign.OrderTimeoutQueryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderTimeoutCancelJob {
    @Autowired
    private OrderTimeoutQueryClient orderTimeoutQueryClient;

    @Autowired
    private OrderStatusUpdateClient orderStatusUpdateClient;
    @Autowired
    private OrderCloseClient orderCloseClient;

    private final WXPay WX_PAY = new WXPay(new PayConfig());

    //@Scheduled(cron = "0/10 * * * * ? ")
    public void checkAndCancelOrder() {
        try {
            //Check orders that have not been paid for more than 30 minutes.
            List<Orders> orders = orderTimeoutQueryClient.query();

            //Cancellation of order.
            //Confirm payment status.
            for (int i = 0; i < orders.size(); i++) {
                Orders order = orders.get(i);
                Map<String, String> map = new HashMap<>();
                map.put("out_trade_no", order.getOrderId());
                Map<String, String> resp = WX_PAY.orderQuery(map);
                if ("success".equalsIgnoreCase(resp.get("trade_state"))) {
                    //Have paid.
                    Orders updateOrder = new Orders();
                    updateOrder.setOrderId(order.getOrderId());
                    updateOrder.setStatus("2");
                    //Call the order-status-update service.
                    int j = orderStatusUpdateClient.update(updateOrder);
                } else {
                    //Non-payment.
                    Map<String, String> resp1 = WX_PAY.closeOrder(map);

                    //Close order.
                    int k = orderCloseClient.close(order.getOrderId(),    1);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
