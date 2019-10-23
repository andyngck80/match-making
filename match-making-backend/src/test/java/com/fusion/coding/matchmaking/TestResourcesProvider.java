package com.fusion.coding.matchmaking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fusion.coding.matchmaking.dto.MatchesRequestDTO;
import com.fusion.coding.matchmaking.dto.RangeDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestResourcesProvider {

    public static String matchesRequestJson(boolean favourite, boolean inContact, boolean hasPhoto, Integer lowerBoundDistance, Integer upperBoundDistance) {
        MatchesRequestDTO matchesRequestDTO = matchRequestDTO(false, false, false, 0.0, 1.0, lowerBoundDistance, upperBoundDistance);
        matchesRequestDTO.setLowerDistanceBoundInKm(lowerBoundDistance);
        matchesRequestDTO.setUpperDistanceBoundInKm(upperBoundDistance);
        return matchesRequestJson(matchesRequestDTO);
    }

    public static String matchesRequestJson(boolean favourite, boolean inContact, boolean hasPhoto, Double minCompatibility, Double maxCompatibility) {
        return matchesRequestJson(matchRequestDTO(favourite, inContact, hasPhoto, minCompatibility, maxCompatibility, null, null));
    }

    private static MatchesRequestDTO matchRequestDTO(boolean favourite, boolean inContact, boolean hasPhoto,
                                                     Double minCompatibility, Double maxCompatibility,
                                                     Integer lowerBoundDistance, Integer upperBoundDistance) {
        return MatchesRequestDTO.builder()
                .favourite(favourite)
                .inContact(inContact)
                .hasPhoto(hasPhoto)
                .compatibilityScore(new RangeDTO<>(minCompatibility, maxCompatibility))
                .lowerDistanceBoundInKm(lowerBoundDistance)
                .upperDistanceBoundInKm(upperBoundDistance)
                .build();
    }

    public static String matchesRequestJson(MatchesRequestDTO matchesRequestDTO) {
        try {
            return new ObjectMapper().writeValueAsString(matchesRequestDTO);
        } catch (JsonProcessingException e) {
            log.error("Unable to serialize object to json string.", e);
            return "{}";
        }
    }
}
