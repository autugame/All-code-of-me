package com.qfedu.fmmall.controller;

import com.github.wxpay.sdk.WXPay;
import com.qfedu.fmmall.entity.Orders;
import com.qfedu.fmmall.service.OrderService;
import com.qfedu.fmmall.service.job.PayConfig;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/order")
@Api(value = "提供订单相关的接口", tags = "订单管理")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    @ApiOperation("添加一个订单")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "cids", value = "购物车id字符串", required = true),
            @ApiImplicitParam(dataType = "Orders", name = "order", value = "order对象", required = true)
    })
    public ResultVO add(String cids, @RequestBody Orders order, @RequestHeader("token") String token) {
        ResultVO resultVO = null;
        try {
            Map<String, String> orderInfo = orderService.addOrder(cids, order);
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

    @GetMapping("/list")
    @ApiOperation("订单查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "userId", value = "用户id", required = true),
            @ApiImplicitParam(dataType = "String", name = "status", value = "订单状态", required = false),
            @ApiImplicitParam(dataType = "int", name = "pageNum", value = "当前页码", required = true),
            @ApiImplicitParam(dataType = "int", name = "limit", value = "分页条数", required = true)
    })
    public ResultVO list(String userId, String status, int pageNum, int limit, @RequestHeader("token") String token) {
        return orderService.listOrders(userId, status, pageNum, limit);
    }
}
