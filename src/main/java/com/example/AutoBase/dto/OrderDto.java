package com.example.AutoBase.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer orderId;
    private String cityName;
    private float destination;
    private String cargoName;
    private float cargoPrice;
    private float weight;
    private boolean isFlight;
}
