package com.qfedu.fmmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 表名：orders
 * 表注释：订单
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrdersVO {
    /**
     * 订单ID 同时也是订单编号
     */
    @Id
    @Column(name = "order_id")
    private String orderId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 产品名称（多个产品用,隔开）
     */
    private String untitled;

    /**
     * 收货人快照
     */
    @Column(name = "receiver_name")
    private String receiverName;

    /**
     * 收货人手机号快照
     */
    @Column(name = "receiver_mobile")
    private String receiverMobile;

    /**
     * 收货地址快照
     */
    @Column(name = "receiver_address")
    private String receiverAddress;

    /**
     * 订单总价格
     */
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    /**
     * 实际支付总价格
     */
    @Column(name = "actual_amount")
    private Integer actualAmount;

    /**
     * 支付方式 1:微信 2:支付宝
     */
    @Column(name = "pay_type")
    private Integer payType;

    /**
     * 订单备注
     */
    @Column(name = "order_remark")
    private String orderRemark;

    /**
     * 订单状态 1:待付款 2:待发货 3:待收货 4:待评价 5:已完成 6:已关闭
     */
    private String status;

    /**
     * 配送方式
     */
    @Column(name = "delivery_type")
    private String deliveryType;

    /**
     * 物流单号
     */
    @Column(name = "delivery_flow_id")
    private String deliveryFlowId;

    /**
     * 订单运费 默认可以为零，代表包邮
     */
    @Column(name = "order_freight")
    private BigDecimal orderFreight;

    /**
     * 逻辑删除状态 1: 删除 0:未删除
     */
    @Column(name = "delete_status")
    private Integer deleteStatus;

    /**
     * 创建时间（成交时间）
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 付款时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 发货时间
     */
    @Column(name = "delivery_time")
    private Date deliveryTime;

    /**
     * 完成时间
     */
    @Column(name = "flish_time")
    private Date flishTime;

    /**
     * 取消时间
     */
    @Column(name = "cancel_time")
    private Date cancelTime;

    /**
     * 订单关闭类型1-超时未支付 2-退款关闭 4-买家取消 15-已通过货到付款交易
     */
    @Column(name = "close_type")
    private Integer closeType;

    private List<OrderItem> orderItems;
}