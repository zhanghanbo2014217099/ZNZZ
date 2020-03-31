package com.znzz.order.zhanghanbo.controller;

import com.znzz.order.zhanghanbo.entities.Order;
import com.znzz.order.zhanghanbo.entities.OrderExample;
import com.znzz.order.zhanghanbo.mapper.OrderMapper;
import com.znzz.order.zhanghanbo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/preOrder")
    public String addOrder(Order order,Model model) {
        try {
            orderService.addOrder(order);
            model.addAttribute("msg", "订单添加完成");
        } catch (Exception e) {
            System.out.println("添加订单失败");
            e.printStackTrace();
        }

        return "tgls/preOrder/preOrder_add";
    }
    @GetMapping("/preOrderList")
    public String getOrderList(Model model){
        List<Order> orderList=orderService.getPreOrderList();
        model.addAttribute("orderlist",orderList);
        return "tgls/preOrder/preOrder_list";
    }
    @GetMapping("/preOrderVerify")
    public String getPrePlanOrderList(Model model){
        List<Order> orderList=orderService.getPrePlanOrderList();
        model.addAttribute("orderlist",orderList);
        return "tgls/preOrder/preOrder_verify";
    }
    @GetMapping(value = "/prePlanOrderVerify/{orderId}/{status}")
    public String modifyStatus(@PathVariable String orderId, @PathVariable Integer status ,Model model){
        orderService.modifyStatus(orderId,status);
        /*List<Order> orderList=orderService.getPrePlanOrderList();
        model.addAttribute("orderlist",orderList);
        return "tgls/preOrder/preOrder_verify";*/
        return "redirect:/preOrderVerify";
    }
}
