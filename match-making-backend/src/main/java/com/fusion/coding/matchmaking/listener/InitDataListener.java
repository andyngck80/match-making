package com.fusion.coding.matchmaking.listener;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fusion.coding.matchmaking.domain.Matches;
import com.fusion.coding.matchmaking.repository.MatchesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class InitDataListener {

    private final MatchesRepository repo;

    @Value("classpath:init-data.json")
    private Resource resourceFile;

    private ObjectMapper matchesObjectMapper = new ObjectMapper();

    public InitDataListener(MatchesRepository repo) {
        this.repo = repo;
        matchesObjectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Matches.class, new MatchesDeserializer());
        matchesObjectMapper.registerModule(module);
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        try {
            List<Matches> matchesList = matchesObjectMapper.readValue(resourceFile.getInputStream(), new TypeReference<List<Matches>>(){});
            log.info("initialize matches list: {}", matchesList);
            repo.saveAll(matchesList);
        } catch (IOException e) {
            log.error("Error parsing json file.", e);
        }
    }
}