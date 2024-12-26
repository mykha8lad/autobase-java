package com.example.AutoBase.service.busines.cityservice;

import com.example.AutoBase.model.City;
import java.util.List;
import java.util.Optional;

public interface CityService {
    void save(City city);
    int[] saveCitiesList(List<City> cities);
    void update(City city);
    void delete(City city);
    List<City> findAll();
    void deleteAll();

    Optional<City> findById(int id);
}
