package com.fusion.coding.matchmaking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MatchesResponseDTO {
    private List<MatchesDTO> matches;
}