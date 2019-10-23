package com.fusion.coding.matchmaking.repository;

import com.fusion.coding.matchmaking.domain.Matches;
import com.fusion.coding.matchmaking.dto.CityDTO;
import com.fusion.coding.matchmaking.dto.MatchFilterDTO;
import com.fusion.coding.matchmaking.dto.RangeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@RequiredArgsConstructor
public class MatchesRepositoryImpl implements MatchesRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Matches> query(MatchFilterDTO filter) {
        Query query = new Query();
        List<Criteria> criteria = new ArrayList<>();

        if (filter.isFavourite()) {
            criteria.add(Criteria.where("favourite").is(filter.isFavourite()));
        }
        if (filter.isHasPhoto()) {
            criteria.add(Criteria.where("mainPhoto").exists(true));
        }
        if (filter.isInContact()) {
            criteria.add(Criteria.where("contactsExchanged").gt(0));
        }

        getRangeCriteria("compatibilityScore", filter.getCompatibilityScore()).ifPresent(c -> criteria.add(c));
        getRangeCriteria("age", filter.getAge()).ifPresent(c -> criteria.add(c));
        getRangeCriteria("heightInCM", filter.getHeight()).ifPresent(c -> criteria.add(c));
        getLocationCriteria(filter).ifPresent(c -> criteria.add(c));

        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        }
        return mongoTemplate.find(query, Matches.class);
    }

    private <T> Optional<Criteria> getRangeCriteria(String field, RangeDTO<T> range) {
        if (range != null) {
            return of(Criteria.where(field)
                    .lte(range.getMax())
                    .gte(range.getMin()));
        }
        return empty();
    }

    private Optional<Criteria> getLocationCriteria(MatchFilterDTO filter) {
        if (filter.getLowerDistanceBoundInKm() != null) {
            return of(Criteria.where("location")
                    .nearSphere(toPoint(filter.getUserLoc()))
                    .maxDistance(new Distance(filter.getLowerDistanceBoundInKm(), Metrics.KILOMETERS).getNormalizedValue()));
        }
        if (filter.getUpperDistanceBoundInKm() != null) {
            return of(Criteria.where("location")
                    .nearSphere(toPoint(filter.getUserLoc()))
                    .minDistance(new Distance(filter.getUpperDistanceBoundInKm(), Metrics.KILOMETERS).getNormalizedValue()));
        }

        return empty();
    }

    private Point toPoint(CityDTO userLoc) {
        return new Point(userLoc.getLon(), userLoc.getLat());
    }

}
