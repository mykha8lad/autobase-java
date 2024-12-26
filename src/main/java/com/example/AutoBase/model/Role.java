package com.example.AutoBase.model;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name", nullable = false)
    private String roleName;
}
