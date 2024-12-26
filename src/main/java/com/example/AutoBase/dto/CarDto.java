package com.example.AutoBase.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Integer carId;
    private String name;
    private float carrying;
    private boolean isFree;
    private boolean isBroker;
}
