package com.example.AutoBase.dao.city;

import com.example.AutoBase.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> findById(int id);
}
