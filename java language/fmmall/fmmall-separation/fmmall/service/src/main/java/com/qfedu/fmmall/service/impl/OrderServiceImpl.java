package com.qfedu.fmmall.service.impl;

import com.qfedu.fmmall.dao.OrderItemMapper;
import com.qfedu.fmmall.dao.OrdersMapper;
import com.qfedu.fmmall.dao.ProductSkuMapper;
import com.qfedu.fmmall.dao.ShoppingCartMapper;
import com.qfedu.fmmall.entity.*;
import com.qfedu.fmmall.service.OrderService;
import com.qfedu.fmmall.utils.PageHelper;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    @Transactional
    public Map<String, String> addOrder(String cids, Orders order) {
        logger.info("Add order to begin.");
        Map<String, String> map = new HashMap<>();
        //1.Query the shopping cart record details based on the 'cids'.
        String[] arr = cids.split(",");
        List<Integer> cidsList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            cidsList.add(Integer.parseInt(arr[i]));
        }
        List<ShoppingCartVO> shoppingCartVOS = shoppingCartMapper.selectShoppingCartByCids(cidsList);

        //2.Check inventory.
        boolean flag = true;
        StringBuilder untitled = new StringBuilder();
        for (int i = 0; i < shoppingCartVOS.size(); i++) {
            ShoppingCartVO sc = shoppingCartVOS.get(i);
            if (Integer.parseInt(sc.getCartNum()) > sc.getSkuStock()) {
                flag = false;
            }
            if (i < shoppingCartVOS.size() - 1) {
                untitled.append(sc.getProductName()).append(",");
            } else {
                untitled.append(sc.getProductName());
            }
        }

        if (flag) {
            logger.info("Stocks are plentiful.");
            //Adequate stock.
            order.setUntitled(untitled.toString());
            order.setCreateTime(new Date());
            order.setStatus("1");

            //Generating order number.
            String orderId = UUID.randomUUID().toString().replace("-", "");
            order.setOrderId(orderId);

            //Save order.
            int i = ordersMapper.insert(order);

            if (i > 0) {
                //Generating product snapshot.
                for (ShoppingCartVO sc : shoppingCartVOS) {
                    String itemId = System.currentTimeMillis() + "" + (new Random().nextInt(89999) + 10000);
                    int cnum = Integer.parseInt(sc.getCartNum());
                    OrderItem orderItem = new OrderItem(itemId, orderId, sc.getProductId(), sc.getProductName(), sc.getProductImg(), sc.getSkuId(), sc.getSkuName(), BigDecimal.valueOf(sc.getSellPrice()), cnum, BigDecimal.valueOf(sc.getSellPrice() * cnum), new Date(), new Date(), 0);
                    int j = orderItemMapper.insert(orderItem);
                }

                //Inventory deduction.
                ProductSku productSku = new ProductSku();
                for (ShoppingCartVO sc : shoppingCartVOS) {
                    int Inventory = sc.getSkuStock() - Integer.parseInt(sc.getCartNum());
                    productSku.setSkuId(sc.getSkuId());
                    productSku.setStock(Inventory);
                    int k = productSkuMapper.updateByPrimaryKeySelective(productSku);
                }
            }

            //Delete purchased shopping cart.
            for (ShoppingCartVO sc : shoppingCartVOS) {
                shoppingCartMapper.deleteByPrimaryKey(sc.getCartId());
            }
            logger.info("Add order completed.");

            map.put("orderId", orderId);
            map.put("productName", untitled.toString());
            return map;
        } else {
            //Understock.
            return null;
        }
    }

    @Override
    public int updateOrderStatus(String orderId, String status) {
        Orders order = new Orders();
        order.setOrderId(orderId);
        order.setStatus(status);
        return ordersMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void closeOrder(String orderId) {
        synchronized (this) {
            Orders cancelOrder = new Orders();
            cancelOrder.setOrderId(orderId);
            cancelOrder.setStatus("6");
            cancelOrder.setCloseType(1);
            ordersMapper.updateByPrimaryKeySelective(cancelOrder);
            //Restocking.
            Example example1 = new Example(OrderItem.class);
            example1.createCriteria()
                    .andEqualTo("orderId", orderId);
            List<OrderItem> orderItems = orderItemMapper.selectByExample(example1);
            for (int j = 0; j < orderItems.size(); j++) {
                OrderItem orderItem = orderItems.get(j);
                ProductSku productSku = productSkuMapper.selectByPrimaryKey(orderItem.getSkuId());
                productSku.setStock(productSku.getStock() + orderItem.getBuyCounts());
                productSkuMapper.updateByPrimaryKey(productSku );
            }
        }
    }

    @Override
    public ResultVO listOrders(String userId, String status, int pageNum, int limit) {
        //paging query.
        int start = (pageNum - 1) * limit;
        List<OrdersVO> ordersVOS = ordersMapper.selectOrders(userId, status, start, limit);

        //Query the total number of records.
        Example example = new Example(Orders.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        if (status != null) {
            criteria.andEqualTo("status", status);
        }
        int count = ordersMapper.selectCountByExample(example);

        //Calculate total pages.
        int pageCount = count % limit == 0 ? count / limit : count / limit + 1;

        return new ResultVO(ResStatus.OK, "success", new PageHelper<OrdersVO>(count, pageCount, ordersVOS));
    }
}
