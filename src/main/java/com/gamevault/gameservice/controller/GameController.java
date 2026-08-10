package com.gamevault.gameservice.controller;

import com.gamevault.gameservice.dto.*;
import com.gamevault.gameservice.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping
    public List<GameDTO> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/{id}")
    public GameDTO getById(@PathVariable UUID id) {
        return gameService.getGameById(id);
    }

    @GetMapping("/latest")
    public List<GameDTO> getLatestGames() {
        return gameService.getLatestGames();
    }

    @GetMapping("/genres")
    public List<GenreDTO> getAllGenres() {
        return gameService.getAllGenres();
    }

    @GetMapping("/platforms")
    public List<PlatformDTO> getAllPlatforms() {
        return gameService.getAllPlatforms();
    }

    @PostMapping
    public GameDTO create(@Valid @RequestBody GameCreateRequest request) {
        return gameService.createGame(request);
    }

    @PutMapping("/{id}")
    public GameDTO update(@PathVariable UUID id,
                          @Valid @RequestBody GameUpdateRequest request) {
        return gameService.updateGame(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        gameService.deleteGame(id);
    }

}
