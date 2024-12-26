package com.example.AutoBase.service.busines.orderservice;

import com.example.AutoBase.dto.OrderDto;
import com.example.AutoBase.dto.OrderFilterDto;
import com.example.AutoBase.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void save(Order order);
    int[] saveOrdersList(List<Order> orders);
    void update(Order order);
    void delete(Order order);
    List<Order> findAll();
    void deleteAll();

    Optional<Order> findById(int id);
    float getTotalPrice(Order order);
    List<Order> findNewOrders();
    List<OrderDto> findAllDto();
    List<OrderDto> findByFilter(OrderFilterDto filterDto);
}
