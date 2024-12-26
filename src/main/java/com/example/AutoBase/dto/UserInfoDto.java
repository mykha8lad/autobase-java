package com.example.AutoBase.dto;

import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    private String name;
    private String numTel;
    private float totalSum;
    private List<String> roles;
}
