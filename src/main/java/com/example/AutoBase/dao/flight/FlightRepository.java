package com.example.AutoBase.dao.flight;

import com.example.AutoBase.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    Optional<Flight> findById(int id);

    @Query("SELECT f FROM Flight f WHERE f.driver.id = :driverId")
    Optional<Flight> findFlightByDriverId(int driverId);
}
