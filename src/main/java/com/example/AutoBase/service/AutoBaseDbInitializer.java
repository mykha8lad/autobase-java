package com.example.AutoBase.service;

import com.example.AutoBase.model.*;
import com.example.AutoBase.service.busines.cargotypeservice.CargoTypeService;
import com.example.AutoBase.service.busines.carservice.CarService;
import com.example.AutoBase.service.busines.cityservice.CityService;
import com.example.AutoBase.service.busines.driverservice.DriverService;
import com.example.AutoBase.service.busines.flightservice.FlightService;
import com.example.AutoBase.service.busines.historyappservice.HistoryAppService;
import com.example.AutoBase.service.busines.orderservice.OrderService;
import com.example.AutoBase.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutoBaseDbInitializer {

    @Value("${data.cities}")
    private String dataCities;

    @Value("${data.cars}")
    private String dataCars;

    @Value("${data.names}")
    private String dataNames;

    @Value("${data.lastnames}")
    private String dataLastnames;

    @Value("${data.cargoTypes}")
    private String dataCargoTypes;


    @Autowired
    private CargoTypeService cargoTypeService;

    @Autowired
    private CarService carService;

    @Autowired
    private CityService cityService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private HistoryAppService historyAppService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private TextFileReader textFileReader;


    private AutoBaseDbInitializer() {}


    public void deleteAllRowsInDB() {
        flightService.deleteAll();
        orderService.deleteAll();
        historyAppService.deleteAll();
        cargoTypeService.deleteAll();
        carService.deleteAll();
        cityService.deleteAll();
        driverService.deleteAll();

        System.out.println("Delete all rows successfully...");
    }

    public void createCities() {
        List<String> citiesName = textFileReader.readTextFile(dataCities);

        List<City> cities = citiesName.stream()
                .map(cityName -> {
                    float destination = TestUtils.getRandomInteger(1, 10) * 100;  // от 100 до 1000 км
                    return new City(0, cityName, destination);
                })
                .toList();
        cityService.saveCitiesList(cities);

        System.out.println("Cities created successfully...");
    }

    public void createCars() {
        List<String> carsName = textFileReader.readTextFile(dataCars);

        List<Car> cars = carsName.stream()
                .map(carName -> {
                    float carrying = TestUtils.getRandomInteger(1, 10) * 100;  // от 10 до 1000 кг
                    return new Car(0, carName, carrying, true, false, 0);
                })
                .toList();
        carService.saveCarsList(cars);

        System.out.println("Cars created successfully...");
    }

    public void createDrivers() {
        List<String> names = textFileReader.readTextFile(dataNames);
        List<String> lastnames = textFileReader.readTextFile(dataLastnames);

        int countDrivers = Math.min(names.size(), lastnames.size());

        List<Driver> drivers = new ArrayList<>();
        for (int i = 0; i < countDrivers; i++) {
            String numTel = TestUtils.getRandomNumTel();
            int experience = TestUtils.getRandomInteger(0, 10);  // от 0 до 10 лет
            drivers.add(new Driver(0, names.get(i), lastnames.get(i), numTel, experience, 0, true, "password", ""));
        }
        driverService.saveDriversList(drivers);

        System.out.println("Drivers created successfully...");
    }

    public void createCargoTypes() {
        List<String> cargoTypesName = textFileReader.readTextFile(dataCargoTypes);

        List<CargoType> cargoTypes = cargoTypesName.stream()
                .map(cargoName -> {
                    int driverExp = TestUtils.getRandomInteger(0, 10);  // от 0 до 10 лет
                    float price = TestUtils.getRandomInteger(1, 10) * 10;  // от 10 до 100
                    return new CargoType(0, cargoName, driverExp, price);
                })
                .toList();
        cargoTypeService.saveCargoTypesList(cargoTypes);

        System.out.println("CargoTypes created successfully...");
    }

    public List<Order> createRandomOrders() {
        List<City> allCities = cityService.findAll();
        List<CargoType> allCargoTypes = cargoTypeService.findAll();

        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < TestUtils.getRandomInteger(1, 3); i++) {
            float weight = TestUtils.getRandomInteger(1, 10) * 100;  // от 100 до 1000 кг
            orders.add(new Order(
                    0,
                    allCities.get(TestUtils.getRandomInteger(0, allCities.size())),
                    allCargoTypes.get(TestUtils.getRandomInteger(0, allCargoTypes.size())),
                    weight,
                    false
            ));
        }

        System.out.println("Orders random created successfully...");
        return orders;
    }
}
