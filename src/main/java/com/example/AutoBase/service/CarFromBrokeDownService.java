package com.example.AutoBase.service;

import com.example.AutoBase.exceptions.CarIsNotFoundException;
import com.example.AutoBase.model.Car;
import com.example.AutoBase.service.busines.carservice.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarFromBrokeDownService {

    @Autowired
    private CarService carService;

    public void carFromBrokeDown(int carId) {
        Car car = carService.findById(carId)
                .orElseThrow(() -> new CarIsNotFoundException("Car cannot find by id: (" + carId + ")"));
        System.out.println("Находим car по id...");

        car.setFree(true);
        car.setBroker(false);
        car.setTimeToRepair(0);

        carService.update(car);
    }
}
