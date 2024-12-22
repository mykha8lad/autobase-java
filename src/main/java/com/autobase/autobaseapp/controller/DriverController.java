package com.autobase.autobaseapp.controller;

import com.autobase.autobaseapp.model.Order;
import com.autobase.autobaseapp.model.User;
import com.autobase.autobaseapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private OrderService orderService;

    // Просмотр своих рейсов
    @GetMapping("/my-orders")
    public String viewMyOrders(@SessionAttribute("user") User user, Model model) {
        model.addAttribute("orders", orderService.getOrdersByDriver(user));
        return "my-orders";
    }

    // Завершение рейса
    @PostMapping("/complete-order")
    public String completeOrder(@RequestParam int orderId) {
        orderService.completeOrder(orderId);
        return "redirect:/driver/my-orders";
    }
}