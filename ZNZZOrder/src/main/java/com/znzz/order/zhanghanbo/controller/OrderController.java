package com.znzz.order.zhanghanbo.controller;

import com.znzz.order.zhanghanbo.entities.Order;
import com.znzz.order.zhanghanbo.entities.OrderExample;
import com.znzz.order.zhanghanbo.entities.OrderProduct;
import com.znzz.order.zhanghanbo.entities.ReturnOrder;
import com.znzz.order.zhanghanbo.mapper.OrderMapper;
import com.znzz.order.zhanghanbo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value="/preOrderProductList/{orderId}")
    public String getPreOrderProductList(@PathVariable String orderId,Model model){
        model.addAttribute("productlist",orderService.getPreOrderProductList(orderId)) ;
        return "tgls/preOrder/preOrder_product";
    }
    @GetMapping("/returnOrderList")
    public String getReturnOrderList(Model model){
        List<ReturnOrder> returnOrderList=orderService.getReturnOrderList();
        model.addAttribute("returnorderlist",returnOrderList);
        return "tgls/returnOrder/returnOrder_list";
    }
    @GetMapping("returnApply")
    public String returnOrderApply(Model model){
        model.addAttribute("orderlist",orderService.getOrderCanReturnList());

        return "tgls/returnOrder/returnOrder_apply";
    }
    @GetMapping(value="/returnOrderProductList/{orderId}")
    public String getReturnOrderProductList(@PathVariable String orderId,Model model){
        model.addAttribute("productlist",orderService.getPreOrderProductList(orderId)) ;
        return "tgls/returnOrder/returnOrder_product";
    }
    @GetMapping("/returnOrderProductApply/{orderId}/{productId}/{productNum}")
    @ResponseBody
    public String returnOrderProductApply(@PathVariable String orderId,@PathVariable String productId ,@PathVariable Float productNum){
        orderService.deleteOrderProduct(orderId,productId);
        orderService.returnOrderApply(orderId,productId,productNum);
        if(!orderService.partReturn(orderId)) {
            orderService.modifyStatus(orderId, 11);
        }else if(orderService.getOrderStatus(orderId)!=10){
            orderService.modifyStatus(orderId,10);
        }
        return "success";
    }
}
