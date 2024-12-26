package com.example.AutoBase.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "num_tel", nullable = false, unique = true)
    private String numTel;

    @Column(name = "experience", nullable = false)
    private int experience;

    @Column(name = "total_sum", nullable = false)
    private float totalSum = 0;

    @Column(name = "is_free", nullable = false)
    private boolean isFree = true;

    @Column(name = "encrypted_password")
    private String encryptedPassword;

    @Transient
    private String passwordConfirm;
}
