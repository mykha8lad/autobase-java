package com.example.AutoBase.service.busines.flightservice;

import com.example.AutoBase.convert.ConvertToTDO;
import com.example.AutoBase.dao.flight.FlightRepository;
import com.example.AutoBase.dto.FlightDto;
import com.example.AutoBase.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ConvertToTDO convertToTDO;


    @Override
    public void save(Flight flight) {
        flightRepository.save(flight);
    }

    @Override
    public int[] saveFlightsList(List<Flight> flights) {
        flightRepository.saveAll(flights);
        return new int[0];
    }

    @Override
    public void update(Flight flight) {
        flightRepository.save(flight);
    }

    @Override
    public void delete(Flight flight) {
        flightRepository.delete(flight);
    }

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public void deleteAll() {
        flightRepository.deleteAll();
    }


    @Override
    public Optional<Flight> findById(int id) {
        return flightRepository.findById(id);
    }

    @Override
    public Optional<FlightDto> findByDriverId(int driverId) {
        Optional<Flight> flight = flightRepository.findFlightByDriverId(driverId);
        return flight.map(f -> convertToTDO.convertToFlightDTO(f));
    }

    @Override
    public List<FlightDto> findAllDto() {
        return getFlightsDto(flightRepository.findAll());
    }

    @Override
    public int passedOneDayWay(Flight flight) {
        int curDay = flight.getCountDayWay();
        if (curDay > 0) {
            flight.setCountDayWay(curDay - 1);
            flightRepository.save(flight);
            return curDay - 1;
        }
        return 0;
    }

    @Override
    public boolean flightBeenCompletedNow(Flight flight) {
        return flight.getCountDayWay() <= 0;
    }


    private List<FlightDto> getFlightsDto(List<Flight> flights) {
        return flights.stream()
                .map(convertToTDO::convertToFlightDTO)
                .collect(Collectors.toList());
    }
}
