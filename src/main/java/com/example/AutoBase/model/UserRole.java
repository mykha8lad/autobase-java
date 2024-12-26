package com.example.AutoBase.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id", nullable = false)
    private Role role;
}
