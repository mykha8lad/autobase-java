package com.example.AutoBase.service;

import com.example.AutoBase.dto.OrderCreateDto;
import com.example.AutoBase.exceptions.CargoTypeIsNotFoundByIdException;
import com.example.AutoBase.exceptions.CityIsNotFoundByIdException;
import com.example.AutoBase.model.CargoType;
import com.example.AutoBase.model.City;
import com.example.AutoBase.model.Order;
import com.example.AutoBase.service.busines.cargotypeservice.CargoTypeService;
import com.example.AutoBase.service.busines.cityservice.CityService;
import com.example.AutoBase.service.busines.orderservice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CityService cityService;

    @Autowired
    private CargoTypeService cargoTypeService;

    public void createOrder(OrderCreateDto orderCreateDto) {
        int cityId = orderCreateDto.getIdCity();
        City city = cityService.findById(cityId).orElseThrow(
                () -> new CityIsNotFoundByIdException("City by id (" + cityId + ") not found...")
        );

        int cargoTypeId = orderCreateDto.getIdCargoType();
        CargoType cargo = cargoTypeService.findById(cargoTypeId).orElseThrow(
                () -> new CargoTypeIsNotFoundByIdException("Cargo type by id (" + cargoTypeId + ") not found...")
        );

        Order order = new Order(0, city, cargo, orderCreateDto.getWeight(), false);
        orderService.save(order);

        System.out.println("Order created: " + order);
    }
}
