package com.example.AutoBase.convert;

import com.example.AutoBase.dto.CarDto;
import com.example.AutoBase.dto.FlightDto;
import com.example.AutoBase.dto.OrderDto;
import com.example.AutoBase.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConvertToTDO {
    public OrderDto convertToOrderDTO(Order order) {
        City city = order.getCity();
        CargoType cargoType = order.getCargoType();
        return new OrderDto(
                order.getId(),
                city.getName(),
                city.getDestination(),
                cargoType.getName(),
                cargoType.getPrice(),
                order.getWeight(),
                order.isFlight()
        );
    }

    public FlightDto convertToFlightDTO(Flight flight) {
        Order order = flight.getOrder();
        City city = order.getCity();
        Car car = flight.getCar();
        Driver driver = flight.getDriver();
        CargoType cargoType = order.getCargoType();
        return new FlightDto(
                flight.getId(),
                city.getName(),
                city.getDestination(),
                cargoType.getName(),
                order.getWeight(),
                driver.getName() + " " + driver.getSurname(),
                car.getName(),
                flight.getCountDayWay()
        );
    }

    public CarDto convertToCarDTO(Car car) {
        return new CarDto(
                car.getId(),
                car.getName(),
                car.getCarrying(),
                car.isFree(),
                car.isBroker()
        );
    }

    public List<OrderDto> convertToOrdersDTO(List<Order> orders) {
        return orders.stream()
                .map(this::convertToOrderDTO)
                .collect(Collectors.toList());
    }

    public List<CarDto> convertToCarsDTO(List<Car> cars) {
        return cars.stream()
                .map(this::convertToCarDTO)
                .collect(Collectors.toList());
    }
}
