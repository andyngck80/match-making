package com.fusion.coding.matchmaking.repository;

import com.fusion.coding.matchmaking.domain.Matches;
import com.fusion.coding.matchmaking.dto.MatchFilterDTO;

import java.util.List;

public interface MatchesRepositoryCustom {
    List<Matches> query(MatchFilterDTO matchFilterDTO);
}