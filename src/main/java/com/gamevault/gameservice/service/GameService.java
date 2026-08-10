package com.gamevault.gameservice.service;

import com.gamevault.gameservice.dto.*;
import com.gamevault.gameservice.entity.Game;
import com.gamevault.gameservice.entity.Genre;
import com.gamevault.gameservice.entity.Platform;
import com.gamevault.gameservice.exception.GameAlreadyExistsException;
import com.gamevault.gameservice.exception.GameNotFoundException;
import com.gamevault.gameservice.exception.GenreNotFoundException;
import com.gamevault.gameservice.exception.PlatformNotFoundException;
import com.gamevault.gameservice.mapper.GameMapper;
import com.gamevault.gameservice.repository.GameRepository;
import com.gamevault.gameservice.repository.GenreRepository;
import com.gamevault.gameservice.repository.PlatformRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GenreRepository genreRepository;
    private final PlatformRepository platformRepository;
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

        Set<Genre> genres = findGenres(request.getGenreIds());
        Set<Platform> platforms = findPlatforms(request.getPlatformIds());

        Game game = mapper.toEntity(request);
        game.setSlug(slug);
        game.setGenres(genres);
        game.setPlatforms(platforms);

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

        Set<Genre> genres = findGenres(request.getGenreIds());
        Set<Platform> platforms = findPlatforms(request.getPlatformIds());

        game.setName(request.getName());
        game.setDescription(request.getDescription());
        game.setGenres(genres);
        game.setPlatforms(platforms);
        game.setReleaseDate(request.getReleaseDate());
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

    private Set<Genre> findGenres(Set<UUID> ids) {

        List<Genre> genres = genreRepository.findAllById(ids);

        if (genres.size() != ids.size()) {
            throw new GenreNotFoundException();
        }

        return new HashSet<>(genres);
    }

    private Set<Platform> findPlatforms(Set<UUID> ids) {

        List<Platform> platforms = platformRepository.findAllById(ids);

        if (platforms.size() != ids.size()) {
            throw new PlatformNotFoundException();
        }

        return new HashSet<>(platforms);
    }

    public List<GenreDTO> getAllGenres() {
        return genreRepository.findAll()
                .stream()
                .map(mapper::toGenreDTO)
                .toList();
    }

    public List<PlatformDTO> getAllPlatforms() {
        return platformRepository.findAll()
                .stream()
                .map(mapper::toPlatformDTO)
                .toList();
    }
}
