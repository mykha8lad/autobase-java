package com.example.AutoBase.service.busines.cargotypeservice;

import com.example.AutoBase.dao.cargoType.CargoTypeRepository;
import com.example.AutoBase.model.CargoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoTypeServiceImpl implements CargoTypeService {

    @Autowired
    private CargoTypeRepository cargoTypeRepository;


    @Override
    public void save(CargoType cargoType) {
        cargoTypeRepository.save(cargoType);
    }

    @Override
    public int[] saveCargoTypesList(List<CargoType> cargoTypes) {
        cargoTypeRepository.saveAll(cargoTypes);
        return new int[0];
    }

    @Override
    public void update(CargoType cargoType) {
        cargoTypeRepository.save(cargoType);
    }

    @Override
    public void delete(CargoType cargoType) {
        cargoTypeRepository.delete(cargoType);
    }

    @Override
    public List<CargoType> findAll() {
        return cargoTypeRepository.findAll();
    }

    @Override
    public void deleteAll() {
        cargoTypeRepository.deleteAll();
    }


    @Override
    public Optional<CargoType> findById(int id) {
        return cargoTypeRepository.findById(id);
    }
}
