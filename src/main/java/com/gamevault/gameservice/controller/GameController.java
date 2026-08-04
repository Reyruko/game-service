package com.gamevault.gameservice.controller;

import com.gamevault.gameservice.dto.GameCreateRequest;
import com.gamevault.gameservice.dto.GameDTO;
import com.gamevault.gameservice.dto.GameUpdateRequest;
import com.gamevault.gameservice.service.GameService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

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
