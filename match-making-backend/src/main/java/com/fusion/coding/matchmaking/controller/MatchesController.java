package com.fusion.coding.matchmaking.controller;

import com.fusion.coding.matchmaking.dto.MatchesRequestDTO;
import com.fusion.coding.matchmaking.dto.MatchesResponseDTO;
import com.fusion.coding.matchmaking.service.MatchesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/match-making")
public class MatchesController {

    private final MatchesService matchesService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/matches",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public MatchesResponseDTO search(@Valid @RequestBody MatchesRequestDTO requestDTO) {
        return matchesService.search(requestDTO);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/matches",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public MatchesResponseDTO getAll() {
        return matchesService.getAll();
    }
}
