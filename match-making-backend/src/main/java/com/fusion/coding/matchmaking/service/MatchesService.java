package com.fusion.coding.matchmaking.service;

import com.fusion.coding.matchmaking.domain.Matches;
import com.fusion.coding.matchmaking.dto.*;
import com.fusion.coding.matchmaking.repository.MatchesRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MatchesService {

    private final MatchesRepository matchesRepository;

    // TODO: for simplicity of demo, this is hardcoded as it is not the purpose of this demo.
    private final User LOGGED_IN_USER = new User("Leeds", -1.548567, 53.801277);

    public MatchesResponseDTO getAll() {
        List<Matches> matches = matchesRepository.findAll();
        return new MatchesResponseDTO(matches.stream()
                .map(this::toMatchesDTO)
                .collect(Collectors.toList()));
    }

    public MatchesResponseDTO search(MatchesRequestDTO matchesRequest) {
        log.debug("Search matches by: {}", matchesRequest);
        List<Matches> matches = matchesRepository.query(toMatchFilterDTO(matchesRequest));
        return new MatchesResponseDTO(matches.stream()
                .map(this::toMatchesDTO)
                .collect(Collectors.toList()));
    }

    private MatchFilterDTO toMatchFilterDTO(MatchesRequestDTO matchesRequest) {
        MatchFilterDTO matchFilterDTO = new MatchFilterDTO();
        BeanUtils.copyProperties(matchesRequest, matchFilterDTO);
        matchFilterDTO.setUserLoc(new CityDTO(LOGGED_IN_USER.getCityName(),
                LOGGED_IN_USER.getLongitude(), LOGGED_IN_USER.getLatitude()));
        return matchFilterDTO;
    }

    private MatchesDTO toMatchesDTO(Matches matches) {
        MatchesDTO matchesDTO = new MatchesDTO();
        BeanUtils.copyProperties(matches, matchesDTO, "city");
        matchesDTO.setCity(toCityCTO(matches.getCity(), matches.getLocation()));
        return matchesDTO;
    }

    private CityDTO toCityCTO(String city, GeoJsonPoint location) {
        return new CityDTO(city, location.getX(), location.getY());
    }

    @Value
    private class User {
        private String cityName;
        private double longitude;
        private double latitude;
    }
}
