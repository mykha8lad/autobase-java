package com.example.AutoBase.dao.car;

import com.example.AutoBase.dto.CarFilterDto;
import com.example.AutoBase.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Integer> {
    @Query("SELECT c FROM Car c WHERE c.carrying >= :carrying AND c.isBroker = false AND c.isFree = true")
    List<Car> findFreeCarsByCarrying(@Param("carrying") float carrying);

    @Query("SELECT c FROM Car c WHERE "
            + "(:#{#filterDto.isFree} IS NULL OR c.isFree = :#{#filterDto.isFree}) AND "
            + "(:#{#filterDto.isBroker} IS NULL OR c.isBroker = :#{#filterDto.isBroker})")
    List<Car> findByFilter(@Param("filterDto") CarFilterDto filterDto);

    Optional<Car> findCarById(int id);
}
