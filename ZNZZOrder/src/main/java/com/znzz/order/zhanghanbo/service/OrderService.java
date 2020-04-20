package com.znzz.order.zhanghanbo.service;

import com.znzz.order.zhanghanbo.entities.*;
import com.znzz.order.zhanghanbo.mapper.OrderMapper;
import com.znzz.order.zhanghanbo.mapper.OrderProductMapper;
import com.znzz.order.zhanghanbo.mapper.ReturnOrderMapper;
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
    @Autowired
    ReturnOrderMapper returnOrderMapper;

    /*
        获取订单列表
     */
    public List<Order> getPreOrderList(){
        OrderExample example=new OrderExample();
        List<Order> orders = orderMapper.selectByExample(example);
        return orders;
    }

    /*
        添加预订单
     */
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
    /*
        获取已经预排产完成的预订单
     */
    public List<Order> getPrePlanOrderList(){
        OrderExample orderExample=new OrderExample();
        OrderExample.Criteria criteria=orderExample.createCriteria();
        //2代表状态为已经完成预排产，可以进行审核
        criteria.andStatusEqualTo(2);
        List<Order> orderList = orderMapper.selectByExample(orderExample);
        return orderList;
    }
    /*
        预订单审核，修改订单状态
     */
    public void modifyStatus(String orderId,Integer status){
        Order order=new Order();
        order.setOrderId(orderId);
        order.setStatus(status);
        orderMapper.updateByPrimaryKeySelective(order);
    }
    /*
        获取预订单产品表
     */
    public List<OrderProduct> getPreOrderProductList(String orderId){
        OrderProductExample orderProductExample=new OrderProductExample();
        OrderProductExample.Criteria criteria=orderProductExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        return  orderProductMapper.selectByExample(orderProductExample);
    }
    /*
        获取订单可以退货的产品表
     */
    public List<OrderProduct> getOrderProductListWithStatus(String orderId){
        OrderProductExample orderProductExample=new OrderProductExample();
        OrderProductExample.Criteria criteria=orderProductExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        criteria.andProductStateEqualTo(0);
        return  orderProductMapper.selectByExample(orderProductExample);
    }
    /*
        获取退单表
     */
    public List<ReturnOrder> getReturnOrderList(){
        ReturnOrderExample returnOrderExample=new ReturnOrderExample();
        return returnOrderMapper.selectByExample(returnOrderExample);
    }
    /*
        退货申请，要修改订单关联的产品表状态
     */
    public void modifyOrderProductStatus(String orderId, String productId) {
        OrderProductExample orderProductExample=new OrderProductExample();
        OrderProductExample.Criteria criteria=orderProductExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        criteria.andProductIdEqualTo(productId);
        OrderProduct orderProduct=new OrderProduct();
        orderProduct.setProductState(1);
        orderProductMapper.updateByExampleSelective(orderProduct,orderProductExample);


    }
    /*
        生成退货单
     */
    public void returnOrderApply(String orderId, String productId, Float productNum) {
        ReturnOrder returnOrder =new ReturnOrder();
        returnOrder.setRorderId(UUID.randomUUID().toString());
        returnOrder.setOrderId(orderId);
        returnOrder.setProductId(productId);
        returnOrder.setStartDate(new Date());
        returnOrder.setRorderNum(productNum);
        returnOrderMapper.insertSelective(returnOrder);
    }
    /*
        退货申请后判断订单产品关联表是否有其他产品，
        若无，则修改订单状态为全部退货 false
        若有，则为部分退货 true
     */
    public boolean partReturn(String orderId){
        OrderProductExample orderProductExample =new OrderProductExample();
        OrderProductExample.Criteria criteria = orderProductExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        criteria.andProductStateEqualTo(0);
        List<OrderProduct> orderProductList = orderProductMapper.selectByExample(orderProductExample);
        if(orderProductList.size()>0){
            return true;
        }
        return false;

    }
    /*
        获取订单状态
     */
    public int getOrderStatus(String orderId){
        return orderMapper.selectByPrimaryKey(orderId).getStatus();
    }
    /*
        获取可以退货的订单
     */
    public List<Order> getOrderCanReturnList(){
        OrderExample example=new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andStatusNotBetween(11,12);

        List<Order> orders = orderMapper.selectByExample(example);
        return orders;
    }
}
