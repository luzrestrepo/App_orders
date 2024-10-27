package com.luzrestrepo.app_orders.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.luzrestrepo.app_orders.entity.Order;
import com.luzrestrepo.app_orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseBody
     public List<Order> createOrder(@RequestBody List<Integer> productIds) {
        return orderService.createOrder(productIds);
    }

    @GetMapping
    @ResponseBody
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/view-orders")
    public String viewOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders";  // This will look for orders.html template
    }

}
