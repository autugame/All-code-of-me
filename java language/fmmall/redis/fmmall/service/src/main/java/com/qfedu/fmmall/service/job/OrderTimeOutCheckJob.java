package com.qfedu.fmmall.service.job;

import com.github.wxpay.sdk.WXPay;
import com.qfedu.fmmall.dao.OrderItemMapper;
import com.qfedu.fmmall.dao.OrdersMapper;
import com.qfedu.fmmall.dao.ProductSkuMapper;
import com.qfedu.fmmall.entity.OrderItem;
import com.qfedu.fmmall.entity.Orders;
import com.qfedu.fmmall.entity.ProductSku;
import com.qfedu.fmmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderTimeOutCheckJob {
    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderService orderService;

    private static final WXPay WX_PAY = new WXPay(new PayConfig());

    @Scheduled(cron = "0/10 * * * * ?")
    public void checkAndCloseOrder() {
        try {
            //Check orders that have not been paid for more than 30 minutes.
            Example example = new Example(Orders.class);
            Date time = new Date(System.currentTimeMillis() - 30*60*1000);
            example.createCriteria()
                    .andEqualTo("status", "1")
                    .andLessThan("createTime", time);
            List<Orders> orders = ordersMapper.selectByExample(example);
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
                    ordersMapper.updateByPrimaryKeySelective(updateOrder);
                } else {
                    //Non-payment.
                    Map<String, String> resp1 = WX_PAY.closeOrder(map);

                    //Close order.
                    orderService.closeOrder(order.getOrderId());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
