package com.example.AutoBase.controller;

import com.example.AutoBase.dto.*;
import com.example.AutoBase.exceptions.*;
import com.example.AutoBase.model.Order;
import com.example.AutoBase.service.CreateOrderService;
import com.example.AutoBase.service.DepartureFlightService;
import com.example.AutoBase.service.busines.cargotypeservice.CargoTypeService;
import com.example.AutoBase.service.busines.cityservice.CityService;
import com.example.AutoBase.service.busines.orderservice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class OrderController {

    @Value("${value.title}")
    private String pageTitle;

    @Value("${value.alertTextColorError}")
    private String alertTextColorError;

    @Value("${value.alertTextColorSuccess}")
    private String alertTextColorSuccess;


    @Autowired
    private OrderService orderService;

    @Autowired
    private CityService cityService;

    @Autowired
    private CargoTypeService cargoTypeService;

    @Autowired
    private CreateOrderService createOrderService;

    @Autowired
    private DepartureFlightService departureFlightService;


    @GetMapping(value = "orders/get")
    public String getOrders(@ModelAttribute OrderFilterDto orderFilterDto, Model model) {
        System.out.println(orderFilterDto);

        List<OrderDto> ordersDto = (orderFilterDto.getIsFlight() == null)
                ? orderService.findAllDto()
                : orderService.findByFilter(orderFilterDto);

        model.addAttribute("title", pageTitle);
        model.addAttribute("ordersDto", ordersDto);
        model.addAttribute("orderFilterDto", orderFilterDto);
        return "orders";
    }


    @GetMapping(value = "order/create")
    public String createOrderPage(Model model) {
        model.addAttribute("title", pageTitle);
        model.addAttribute("cities", cityService.findAll());
        model.addAttribute("cargoTypes", cargoTypeService.findAll());
        return "createOrder";
    }

    @PostMapping(value = "order/create")
    public String createOrder(@ModelAttribute OrderCreateDto orderCreateDto, Model model, RedirectAttributes redirectAttributes) {
        System.out.println(orderCreateDto.toString());

        MessageDto messageDto = new MessageDto();
        try {
            createOrderService.createOrder(orderCreateDto);  // создаём заявку
            messageDto.setMessage("Order successfully created");
            messageDto.setColor(alertTextColorSuccess);
        } catch (CityIsNotFoundByIdException | CargoTypeIsNotFoundByIdException e) {
            messageDto.setMessage(e.getMessage());
            messageDto.setColor(alertTextColorError);
        }
        redirectAttributes.addFlashAttribute("message", messageDto);

        return "redirect:/orders/get";
    }


    @PostMapping(value = "order/to-flight")
    public String orderToFlight(@RequestParam("orderId") int orderId, Model model, RedirectAttributes redirectAttributes) {
        MessageDto messageDto = new MessageDto();
        try {
            departureFlightService.departureFlight(orderId);  // отправляем в рейс
            messageDto.setMessage("The order has successfully set off on flight");
            messageDto.setColor(alertTextColorSuccess);
        } catch (OrderIsNotFoundByIdException | CarIsNotFoundException | DriverIsNotFoundException | NullPointerException e) {
            messageDto.setMessage(e.getMessage());
            messageDto.setColor(alertTextColorError);
        }
        redirectAttributes.addFlashAttribute("message", messageDto);

        return "redirect:/orders/get";
    }

    @PostMapping(value = "order/delete")
    public String deleteOrder(@RequestParam("orderId") int orderId, Model model, RedirectAttributes redirectAttributes) {
        MessageDto messageDto = new MessageDto();

        Order order = orderService.findById(orderId).orElse(null);
        if (order == null) {
            messageDto.setMessage("Order cannot find");
            messageDto.setColor(alertTextColorError);
        }
        else {
            orderService.delete(order);  // удаляем заявку

            messageDto.setMessage("Order deleted successfully");
            messageDto.setColor(alertTextColorSuccess);

            redirectAttributes.addFlashAttribute("message", messageDto);
        }

        return "redirect:/orders/get";
    }
}
