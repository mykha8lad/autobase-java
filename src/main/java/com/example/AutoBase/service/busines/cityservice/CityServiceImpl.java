package com.example.AutoBase.service.busines.cityservice;

import com.example.AutoBase.dao.city.CityRepository;
import com.example.AutoBase.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;


    @Override
    public void save(City city) {
        cityRepository.save(city);
    }

    @Override
    public int[] saveCitiesList(List<City> cities) {
        cityRepository.saveAll(cities);
        return new int[0];
    }

    @Override
    public void update(City city) {
        cityRepository.save(city);
    }

    @Override
    public void delete(City city) {
        cityRepository.delete(city);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public void deleteAll() {
        cityRepository.deleteAll();
    }


    @Override
    public Optional<City> findById(int id) {
        return cityRepository.findById(id);
    }
}
