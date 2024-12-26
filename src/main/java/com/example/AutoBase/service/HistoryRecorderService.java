package com.example.AutoBase.service;

import com.example.AutoBase.model.*;
import com.example.AutoBase.service.busines.historyappservice.HistoryAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HistoryRecorderService {

    @Autowired
    private HistoryAppService historyAppService;

    public void recordToHistory(Order order, Driver driver, Car car, Flight flight, boolean carIsBroker) {
        CargoType cargoType = order.getCargoType();
        HistoryApp historyApp = new HistoryApp();
        historyApp.setDriverName(driver.getName());
        historyApp.setDriverNumTel(driver.getNumTel());
        historyApp.setCity(order.getCity());
        historyApp.setCargoDriverExp(cargoType.getDriverExp());
        historyApp.setCargoPrice(cargoType.getPrice());
        historyApp.setCargoWeight(order.getWeight());
        historyApp.setCarName(car.getName());
        historyApp.setCarCarrying(car.getCarrying());
        historyApp.setStartDate(flight.getStartDate());

        LocalDateTime endDate = carIsBroker ? null : LocalDateTime.now();
        historyApp.setEndDate(endDate);

        historyAppService.save(historyApp);
    }
}
