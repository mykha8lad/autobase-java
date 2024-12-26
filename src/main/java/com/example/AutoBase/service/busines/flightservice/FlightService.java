package com.example.AutoBase.service.busines.flightservice;

import com.example.AutoBase.dto.FlightDto;
import com.example.AutoBase.model.Flight;
import java.util.List;
import java.util.Optional;

public interface FlightService {
    void save(Flight flight);
    int[] saveFlightsList(List<Flight> flights);
    void update(Flight flight);
    void delete(Flight flight);
    List<Flight> findAll();
    void deleteAll();

    Optional<Flight> findById(int id);
    Optional<FlightDto> findByDriverId(int driverId);
    List<FlightDto> findAllDto();
    int passedOneDayWay(Flight flight);
    boolean flightBeenCompletedNow(Flight flight);
}
