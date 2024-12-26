package com.example.AutoBase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoBaseInitializer {

    @Autowired
    private AutoBaseDbInitializer autoBaseDbInitializer;

    public void autoBaseInitialize() {
        System.out.println("-".repeat(20));

        autoBaseDbInitializer.deleteAllRowsInDB();
        autoBaseDbInitializer.createCities();
        autoBaseDbInitializer.createCars();
        autoBaseDbInitializer.createDrivers();
        autoBaseDbInitializer.createCargoTypes();

        System.out.println("-".repeat(20));
    }
}
