package com.gamevault.gameservice.service;

import com.gamevault.gameservice.dto.GameCreateRequest;
import com.gamevault.gameservice.dto.GameDTO;
import com.gamevault.gameservice.dto.GameUpdateRequest;
import com.gamevault.gameservice.entity.Game;
import com.gamevault.gameservice.exception.GameAlreadyExistsException;
import com.gamevault.gameservice.exception.GameNotFoundException;
import com.gamevault.gameservice.mapper.GameMapper;
import com.gamevault.gameservice.repository.GameRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper mapper;

    public List<GameDTO> getAllGames() {
        return gameRepository.findAll().stream().map(mapper::toDTO).toList();
    }

    public GameDTO getGameById(UUID id) {
        
        Game game = gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);

        return mapper.toDTO(game);
    }

    public List<GameDTO> getLatestGames() {

        return gameRepository.findTop5ByOrderByCreatedOnDesc()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Transactional
    public GameDTO createGame(GameCreateRequest request) {

        String slug = generateSlug(request.getName());

        if (gameRepository.existsBySlug(slug)) {
            throw new GameAlreadyExistsException(request.getName());
        }

        Game game = mapper.toEntity(request);
        game.setSlug(slug);

        return mapper.toDTO(gameRepository.save(game));
    }

    @Transactional
    public GameDTO updateGame(UUID id, GameUpdateRequest request) {

        Game game = gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);

        String slug = generateSlug(request.getName());

        if (!slug.equals(game.getSlug())
                && gameRepository.existsBySlug(slug)) {

            throw new GameAlreadyExistsException(request.getName());
        }

        game.setName(request.getName());
        game.setDeveloper(request.getDeveloper());
        game.setPublisher(request.getPublisher());
        game.setGenre(request.getGenre());
        game.setDescription(request.getDescription());
        game.setPlatforms(request.getPlatforms());
        game.setReleaseDate(request.getReleaseDate());
        game.setCoverImage(request.getCoverImage());

        game.setSlug(slug);

        return mapper.toDTO(gameRepository.save(game));
    }

    @Transactional
    public void deleteGame(UUID id) {

        Game game = gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);

        gameRepository.delete(game);
    }

    private String generateSlug(String name) {
        return name.toLowerCase()
                .trim()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
    }

}
