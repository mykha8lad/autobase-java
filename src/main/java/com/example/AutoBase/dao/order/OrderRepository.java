package com.example.AutoBase.dao.order;

import com.example.AutoBase.dto.OrderFilterDto;
import com.example.AutoBase.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findById(int id);

    @Query("SELECT o FROM Order o WHERE o.isFlight = false")
    List<Order> findByFlightIsFalse();

    @Query("SELECT o FROM Order o WHERE "
            + "(:#{#filterDto.isFlight} IS NULL OR o.isFlight = :#{#filterDto.isFlight})"
    )
    List<Order> findByFilter(@Param("filterDto") OrderFilterDto filterDto);
}
