package com.example.AutoBase.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order", referencedColumnName = "id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_car", referencedColumnName = "id", nullable = false)
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_driver", referencedColumnName = "id", nullable = false)
    private Driver driver;

    @Column(name = "count_day_way", nullable = false)
    private int countDayWay;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;
}
