package com.fusion.coding.matchmaking.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;

@Builder
@Data
public class MatchesRequestDTO {
    private boolean hasPhoto;
    private boolean inContact;
    private boolean favourite;
    @Valid
    private RangeDTO<Double> compatibilityScore;
    @Valid
    private RangeDTO<Integer> age;
    @Valid
    private RangeDTO<Integer> height;
    private Integer lowerDistanceBoundInKm;
    private Integer upperDistanceBoundInKm;
}
