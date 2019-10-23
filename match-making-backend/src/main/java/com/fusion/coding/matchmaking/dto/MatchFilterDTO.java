package com.fusion.coding.matchmaking.dto;

import lombok.Data;


@Data
public class MatchFilterDTO {
    private boolean hasPhoto;
    private boolean inContact;
    private boolean favourite;
    private RangeDTO<Double> compatibilityScore;
    private RangeDTO<Integer> age;
    private RangeDTO<Integer> height;
    private Integer lowerDistanceBoundInKm;
    private Integer upperDistanceBoundInKm;
    private CityDTO userLoc;
}
