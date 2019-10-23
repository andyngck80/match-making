package com.fusion.coding.matchmaking.listener;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fusion.coding.matchmaking.domain.Matches;
import com.fusion.coding.matchmaking.domain.Religion;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.io.IOException;

public class MatchesDeserializer extends StdDeserializer<Matches> {

    public MatchesDeserializer() {
        this(null);
    }

    public MatchesDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Matches deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        Matches matches = new Matches();
        matches.setDisplayName(node.get("display_name").textValue());
        matches.setAge(node.get("age").intValue());
        matches.setJobTitle(node.get("job_title").textValue());
        matches.setHeightInCM(node.get("height_in_cm").intValue());
        matches.setMainPhoto(getValueSafe(node.get("main_photo")));
        matches.setCompatibilityScore(node.get("compatibility_score").doubleValue());
        matches.setContactsExchanged(node.get("contacts_exchanged").longValue());
        matches.setFavourite(node.get("favourite").booleanValue());
        matches.setReligion(Religion.valueOf(node.get("religion").textValue()));

        JsonNode locationNode = node.get("city");
        GeoJsonPoint location = toGeoJsonPoint(locationNode);
        matches.setLocation(location);
        matches.setCity(locationNode.get("name").textValue());

        return matches;
    }

    private GeoJsonPoint toGeoJsonPoint(JsonNode locationNode) {
        double lon = locationNode.get("lon").doubleValue();
        double lat = locationNode.get("lat").doubleValue();
        return new GeoJsonPoint(lon, lat);
    }

    private String getValueSafe(JsonNode node) {
        return node != null ? node.textValue() : null;
    }
}
