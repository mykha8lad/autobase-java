package com.example.AutoBase.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDto {
    private int idCity;
    private int idCargoType;
    private float weight;
}
