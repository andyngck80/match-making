package com.fusion.coding.matchmaking.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Matches {

    @Id
    private String id;
    private String displayName;
    private Integer age;
    private String jobTitle;
    private Integer heightInCM;
    private String city;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;
    private String mainPhoto;
    private Double compatibilityScore;
    private Long contactsExchanged;
    private Boolean favourite;
    private Religion religion;
}
