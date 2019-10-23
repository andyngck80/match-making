package com.fusion.coding.matchmaking.repository;

import com.fusion.coding.matchmaking.domain.Matches;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MatchesRepository extends MongoRepository<Matches, String>, MatchesRepositoryCustom {
    List<Matches> findByLocationNear(Point p, Distance d);
}