package com.autobase.autobaseapp.service;

import com.autobase.autobaseapp.model.Order;
import com.autobase.autobaseapp.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private List<Order> orders = new ArrayList<>();
    private int nextId = 1; // Для генерации ID заявок

    // Добавление заявки
    public void addOrder(String destination, double weight, String cargoType) {
        orders.add(new Order(nextId++, destination, weight, cargoType));
    }

    // Получение всех заявок
    public List<Order> getAllOrders() {
        return orders;
    }

    // Назначение заявки на водителя
    public boolean assignOrder(int orderId, User driver, String vehicle) {
        for (Order order : orders) {
            if (order.getId() == orderId && order.getStatus().equals("Новый")) {
                order.setAssignedDriver(driver);
                order.setAssignedVehicle(vehicle);
                order.setStatus("В пути");
                return true;
            }
        }
        return false;
    }

    // Получение заявок для конкретного водителя
    public List<Order> getOrdersByDriver(User driver) {
        List<Order> driverOrders = new ArrayList<>();
        for (Order order : orders) {
            if (driver.equals(order.getAssignedDriver())) {
                driverOrders.add(order);
            }
        }
        return driverOrders;
    }

    // Завершение рейса
    public boolean completeOrder(int orderId) {
        for (Order order : orders) {
            if (order.getId() == orderId && order.getStatus().equals("В пути")) {
                order.setStatus("Выполнен");
                return true;
            }
        }
        return false;
    }
}