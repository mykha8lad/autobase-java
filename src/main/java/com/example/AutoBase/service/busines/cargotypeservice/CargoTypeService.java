package com.example.AutoBase.service.busines.cargotypeservice;

import com.example.AutoBase.model.CargoType;
import java.util.List;
import java.util.Optional;

public interface CargoTypeService {
    void save(CargoType cargoType);
    int[] saveCargoTypesList(List<CargoType> cargoTypes);
    void update(CargoType cargoType);
    void delete(CargoType cargoType);
    List<CargoType> findAll();
    void deleteAll();

    Optional<CargoType> findById(int id);
}
