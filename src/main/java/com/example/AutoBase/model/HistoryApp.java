package com.example.AutoBase.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "historyapp")
public class HistoryApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "driver_name", nullable = false)
    private String driverName;

    @Column(name = "driver_num_tel", nullable = false)
    private String driverNumTel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_city", referencedColumnName = "id", nullable = false)
    private City city;

    @Column(name = "cargo_driver_exp", nullable = false)
    private int cargoDriverExp;

    @Column(name = "cargo_price", nullable = false)
    private float cargoPrice;

    @Column(name = "cargo_weight", nullable = false)
    private float cargoWeight;

    @Column(name = "car_name", nullable = false)
    private String carName;

    @Column(name = "car_carrying", nullable = false)
    private float carCarrying;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;
}
