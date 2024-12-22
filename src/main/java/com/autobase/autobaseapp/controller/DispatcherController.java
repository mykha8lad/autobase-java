package com.autobase.autobaseapp.controller;

import com.autobase.autobaseapp.model.Order;
import com.autobase.autobaseapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dispatcher")
public class DispatcherController {

    @Autowired
    private OrderService orderService;

    // Страница добавления заявки
    @GetMapping("/add-order")
    public String addOrderPage() {
        return "add-order";
    }

    // Обработка добавления заявки
    @PostMapping("/add-order")
    public String addOrder(@RequestParam String destination,
                           @RequestParam double weight,
                           @RequestParam String cargoType) {
        orderService.addOrder(destination, weight, cargoType);
        return "redirect:/dispatcher/orders";
    }

    // Отображение всех заявок
    @GetMapping("/orders")
    public String viewOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }
}