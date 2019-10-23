package com.fusion.coding.matchmaking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RangeDTO<T> {
    @NotNull
    private T min;
    @NotNull
    private T max;
}