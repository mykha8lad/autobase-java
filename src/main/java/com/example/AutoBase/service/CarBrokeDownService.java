package com.example.AutoBase.service;

import com.example.AutoBase.exceptions.FlightIsNotFoundByException;
import com.example.AutoBase.model.*;
import com.example.AutoBase.service.busines.carservice.CarService;
import com.example.AutoBase.service.busines.driverservice.DriverService;
import com.example.AutoBase.service.busines.flightservice.FlightService;
import com.example.AutoBase.service.busines.orderservice.OrderService;
import com.example.AutoBase.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class CarBrokeDownService {

    @Autowired
    private FlightService flightService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private CarService carService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private HistoryRecorderService historyRecorderService;


    public void carBrokerDown(int idFlight) {
        // находим flight по id
        Flight flight = flightService.findById(idFlight)
                .orElseThrow(() -> new FlightIsNotFoundByException("Flight cannot find by id: (" + idFlight + ")"));
        System.out.println("Находим flight по id...");

        Order order = Objects.requireNonNull(flight.getOrder(), "Order cannot be null...");
        Driver driver = Objects.requireNonNull(flight.getDriver(), "Driver cannot be null...");
        Car car = Objects.requireNonNull(flight.getCar(), "Car cannot be null...");

        // заносим в историю
        historyRecorderService.recordToHistory(order, driver, car, flight, true);
        System.out.println("Заносим в историю...");

        // удаляем flight и order
        flightService.delete(flight);
        orderService.delete(order);
        System.out.println("Удаляем flight и order...");

        // машину в ремонт
        int timeToRepair = TestUtils.getRandomInteger(1, 3);
        car.setTimeToRepair(timeToRepair);
        car.setBroker(true);
        car.setFree(false);
        carService.update(car);
        System.out.println("Машину в ремонт...");

        // освобождаем водителя
        driver.setFree(true);
        driverService.update(driver);
        System.out.println("Освобождаем водителя...");
    }
}
