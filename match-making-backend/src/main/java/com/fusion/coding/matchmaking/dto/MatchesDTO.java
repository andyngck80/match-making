package com.fusion.coding.matchmaking.dto;

import lombok.Data;

@Data
public class MatchesDTO {
    private String displayName;
    private Integer age;
    private String jobTitle;
    private Integer heightInCm;
    private CityDTO city;
    private String mainPhoto;
    private Double compatibilityScore;
    private Long contactsExchanged;
    private Boolean favourite;
    private String religion;
}
