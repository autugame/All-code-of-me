package com.qfedu.api.controller;

import com.github.wxpay.sdk.WXPay;
import com.qfedu.api.config.PayConfig;
import com.qfedu.api.service.OrderSubmitService;
import com.qfedu.api.service.impl.SendMsgToMqService;
import com.qfedu.fmmall.entity.Orders;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderSubmitController {
    @Autowired
    private OrderSubmitService orderSubmitService;
    @Autowired
    private SendMsgToMqService sendMsgToMqService;

    @PostMapping("/add")
    public ResultVO add(String cids, @RequestBody Orders order, @RequestHeader("token") String token) {
        ResultVO resultVO = null;
        try {
            Map<String, String> orderInfo = orderSubmitService.addOrder(cids, order);
            String orderId = orderInfo.get("orderId");
            String productName = orderInfo.get("productName");

            if (orderId != null) {
                //WeChat pay: request payment link.
                WXPay wxPay = new WXPay(new PayConfig());

                //Set the current order information.
                Map<String, String> data = new HashMap<>();
                data.put("body", productName);                                     // Product description.
                data.put("out_trade_no", orderId);          // Use the id of the current order as the payment id.
                data.put("fee_type", "CNY");                                    // Currency of payment.
                data.put("total_fee", order.getActualAmount() * 100 + "");                                     // Payment amount.
//                data.put("total_fee", "1");
                data.put("trade_type", "NATIVE");                               // Trade type.
                data.put("notify_url", "http://wechatpay.free.idcfengye.com/pay/callback");                                     // Set the payment completion callback method interface.

                //Send a request and get a response.
                Map<String, String> res = wxPay.unifiedOrder(data);

                orderInfo.put("payUrl", res.get("code_url"));

                resultVO = new ResultVO(ResStatus.OK, "Order submission succeeded", orderInfo);
                sendMsgToMqService.sendMsg(orderId);
            } else {
                resultVO = new ResultVO(ResStatus.OK, "Order submission failed");
            }
        } catch (SQLException e) {
            resultVO = new ResultVO(ResStatus.OK, "Order submission failed");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultVO;
    }
}
