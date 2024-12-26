package com.example.AutoBase.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {
    private Integer flightId;
    private String cityName;
    private float destination;
    private String cargoName;
    private float weight;
    private String driverName;
    private String carName;
    private int countDayWay;
}
