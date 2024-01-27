package com.qfedu.fmmall.controller;

import com.github.wxpay.sdk.WXPayUtil;
import com.qfedu.fmmall.service.OrderService;
import com.qfedu.fmmall.webSocket.WebSocketServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pay")
@Api(value = "支付平台回调接口", tags = "支付管理")
public class PayController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/callback")
    @ApiOperation("回调接口")
    public String callback(HttpServletRequest request) throws Exception {
        ServletInputStream is = request.getInputStream();
        byte[] bs = new byte[1024];
        int len = -1;
        StringBuilder stringBuilder = new StringBuilder();
        while ((len = is.read(bs)) != -1) {
            stringBuilder.append(new String(bs, 0, len));
        }
        String s = stringBuilder.toString();
        Map<String, String> map = null;

        if (!s.isEmpty()) {
            map = WXPayUtil.xmlToMap(s);
        }

        if (map != null && "success".equalsIgnoreCase(map.get("result_code"))) {
            //Payment success.
            //Modify order status.
            String orderId = map.get("out_trade_no");
            int i = orderService.updateOrderStatus(orderId, "2");
            if (i > 0) {
                //Push notification.
                WebSocketServer.sendMsg(orderId, "1");
                //Respond to WeChat pay.
                Map<String, String> resp = new HashMap<>();
                resp.put("return_code", "success");
                resp.put("return_msg", "OK");
                resp.put("appid", map.get("appid"));
                resp.put("result_code", "success");
                return WXPayUtil.mapToXml(resp);
            }
        }
        return null;
    }
}
