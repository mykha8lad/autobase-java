package com.example.AutoBase.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cargotype")
public class CargoType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "driver_exp", nullable = false)
    private int driverExp;

    @Column(name = "price", nullable = false)
    private float price;
}
