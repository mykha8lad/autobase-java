package com.example.AutoBase.service;

import com.example.AutoBase.exceptions.FlightIsNotFoundByException;
import com.example.AutoBase.model.Flight;
import com.example.AutoBase.service.busines.flightservice.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassedOneDayFlightService {

    @Autowired
    private FlightService flightService;

    @Autowired
    CompletedFlightService completedFlightService;


    public boolean passedOneDayFlight(int idFlight) {
        // находим flight по id
        Flight flight = flightService.findById(idFlight)
                .orElseThrow(() -> new FlightIsNotFoundByException("Flight cannot find by id: (" + idFlight + ")"));
        System.out.println("Находим flight по id...");

        // отнимаем один день пути
        int countLeftDayWay = flightService.passedOneDayWay(flight);
        System.out.println("Отнимаем один день пути...");

        // если приехали, то рейс завершен
        if (countLeftDayWay <= 0) {
            System.out.println("COMPLETED FLIGHTS!!!");
            completedFlightService.completedFlight(flight);
        }

        return countLeftDayWay <= 0;
    }
}
