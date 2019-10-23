package com.fusion.coding.matchmaking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CityDTO {

    private String name;
    private Double lon;
    private Double lat;
}