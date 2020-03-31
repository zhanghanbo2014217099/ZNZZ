package com.znzz.order.zhanghanbo.service;

import com.znzz.order.zhanghanbo.entities.Order;
import com.znzz.order.zhanghanbo.entities.OrderExample;
import com.znzz.order.zhanghanbo.entities.OrderProduct;
import com.znzz.order.zhanghanbo.mapper.OrderMapper;
import com.znzz.order.zhanghanbo.mapper.OrderProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderProductMapper orderProductMapper;
    public List<Order> getPreOrderList(){
        OrderExample example=new OrderExample();
        List<Order> orders = orderMapper.selectByExample(example);
        return orders;
    }
    public void addOrder(Order order){
        String orderId=UUID.randomUUID().toString();
        Order record=new Order();
        record.setOrderId(orderId);
        record.setOrderDate(new Date());
        record.setExpectDate(order.getExpectDate());
        record.setCustomerId(order.getCustomerId());
        record.setSourceType("商城");
        record.setStatus(1);
        record.setEmployeeId(order.getEmployeeId());
        record.setNote(order.getNote());
        record.setTotalPrice(new BigDecimal(30000));
        orderMapper.insertSelective(record);
        for(int i=0;i<order.getProductList().size();i++){
            OrderProduct orderProduct=new OrderProduct();
            orderProduct.setOrderId(orderId);
            orderProduct.setProductId(order.getProductList().get(i).getProductId());
            orderProduct.setProductNum(order.getProductList().get(i).getProductNum());
            orderProductMapper.insertSelective(orderProduct);
        }


    }
    public List<Order> getPrePlanOrderList(){
        OrderExample orderExample=new OrderExample();
        OrderExample.Criteria criteria=orderExample.createCriteria();
        //2代表状态为已经完成预排产，可以进行审核
        criteria.andStatusEqualTo(2);
        List<Order> orderList = orderMapper.selectByExample(orderExample);
        return orderList;
    }

    public void modifyStatus(String orderId,Integer status){
        Order order=new Order();
        order.setOrderId(orderId);
        order.setStatus(status);
        orderMapper.updateByPrimaryKeySelective(order);
    }
}
