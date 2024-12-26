package com.example.AutoBase.service;

import com.example.AutoBase.exceptions.FlightCannotBeCanceledException;
import com.example.AutoBase.model.*;
import com.example.AutoBase.service.busines.carservice.CarService;
import com.example.AutoBase.service.busines.driverservice.DriverService;
import com.example.AutoBase.service.busines.flightservice.FlightService;
import com.example.AutoBase.service.busines.orderservice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class CompletedFlightService {

    @Autowired
    private FlightService flightService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private CarService carService;

    @Autowired
    private HistoryRecorderService historyRecorderService;


    public void completedFlight(Flight flight) {
        Flight validFlight = Objects.requireNonNull(flight, "Flight cannot be null...");

        // проверка что flight доехал
        if (!flightService.flightBeenCompletedNow(flight)) {
            throw new FlightCannotBeCanceledException("Flight cannot be canceled, flight on the road...");
        }

        Order order = Objects.requireNonNull(validFlight.getOrder(), "Order cannot be null...");
        Driver driver = Objects.requireNonNull(validFlight.getDriver(), "Driver cannot be null...");
        Car car = Objects.requireNonNull(validFlight.getCar(), "Car cannot be null...");

        // водитель получает деньги
        float sumFlight = orderService.getTotalPrice(order);
        System.out.println("sumFlight: " + sumFlight);
        driverService.accrualOfMoney(driver, sumFlight);
        System.out.println("Водитель получает деньги...");

        // освобождаем водителя и машину
        releaseDriverAndCar(driver, car);
        System.out.println("Освобождаем водителя и машину...");

        // заносим в историю
        historyRecorderService.recordToHistory(order, driver, car, validFlight, false);
        System.out.println("Заносим в историю...");

        // удаляем flight и order
        flightService.delete(validFlight);
        orderService.delete(order);
        System.out.println("Удаляем flight и order...");
    }

    private void releaseDriverAndCar(Driver driver, Car car) {
        // освобождаем водителя
        driver.setFree(true);
        driverService.update(driver);

        // освобождаем машину
        car.setFree(true);
        carService.update(car);
    }
}
