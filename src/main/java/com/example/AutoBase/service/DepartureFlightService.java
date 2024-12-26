package com.example.AutoBase.service;

import com.example.AutoBase.exceptions.*;
import com.example.AutoBase.model.*;
import com.example.AutoBase.service.busines.carservice.CarService;
import com.example.AutoBase.service.busines.driverservice.DriverService;
import com.example.AutoBase.service.busines.flightservice.FlightService;
import com.example.AutoBase.service.busines.orderservice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Transactional
public class DepartureFlightService {

    @Value("${value.averageCarSpeed}")
    private int averageCarSpeed;

    @Autowired
    private OrderService orderService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private CarService carService;

    @Autowired
    private DriverService driverService;


    public void departureFlight(int idOrder) {
        // находим order по id
        Order order = orderService.findById(idOrder)
                .orElseThrow(() -> new OrderIsNotFoundByIdException("Order cannot find by id: (" + idOrder + ")"));
        System.out.println("Находим order по id...");

        CargoType cargoType = Objects.requireNonNull(order.getCargoType(), "Cargo type cannot be null...");
        City city = Objects.requireNonNull(order.getCity(), "City cannot be null...");

        // находим свободную машину
        Car car = carService.findFreeCarByCarrying(order.getWeight())
                .orElseThrow(() -> new CarIsNotFoundException("Free car for carrying " + order.getWeight() + " not found..."));
        System.out.println("Находим свободную машину...");

        // находим водителя с нужным опытом
        Driver driver = driverService.findFreeDriverByExperience(cargoType.getDriverExp())
                .orElseThrow(() -> new DriverIsNotFoundException("Free driver for experience " + cargoType.getDriverExp() + " not found..."));
        System.out.println("Находим водителя с нужным опытом...");

        // считаем продолжительность дней поездки
        float average = city.getDestination() / averageCarSpeed;
        int countDayWay = (int) Math.ceil(average);
        System.out.println("Считаем продолжительность дней поездки...");

        // создаём Flight
        Flight flight = new Flight(0, order, car, driver, countDayWay, LocalDateTime.now());
        flightService.save(flight);
        System.out.println("Создаём Flight...");

        // устанавливаем флаг в driver
        driver.setFree(false);
        driverService.update(driver);

        // устанавливаем флаг в car
        car.setFree(false);
        carService.update(car);

        // устанавливаем флаг в order
        order.setFlight(true);
        orderService.update(order);
        System.out.println("Устанавливаем флаг в order...");
    }
}
