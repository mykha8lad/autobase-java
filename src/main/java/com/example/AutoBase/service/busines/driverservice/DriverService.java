package com.example.AutoBase.service.busines.driverservice;

import com.example.AutoBase.model.Driver;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    Driver save(Driver driver);
    int[] saveDriversList(List<Driver> drivers);
    void update(Driver driver);
    void delete(Driver driver);
    List<Driver> findAll();
    void deleteAll();

    Optional<Driver> findFreeDriverByExperience(int experience);
    Optional<Driver> findByName(String name);
    Optional<Driver> findByNumTel(String numTel);
    void accrualOfMoney(Driver driver, float money);
}
