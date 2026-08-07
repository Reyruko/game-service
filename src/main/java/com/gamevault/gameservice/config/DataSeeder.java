package com.gamevault.gameservice.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamevault.gameservice.dto.GameSeedDTO;
import com.gamevault.gameservice.entity.Game;
import com.gamevault.gameservice.entity.Genre;
import com.gamevault.gameservice.entity.Platform;
import com.gamevault.gameservice.repository.GameRepository;
import com.gamevault.gameservice.repository.GenreRepository;
import com.gamevault.gameservice.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final GenreRepository genreRepository;
    private final PlatformRepository platformRepository;
    private final GameRepository gameRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        seedGames();
    }

    private void seedGames() throws Exception {
        if (gameRepository.count() > 0) {
            return;
        }

        ClassPathResource resource = new ClassPathResource("data/games.json");

        List<GameSeedDTO> seeds =
                objectMapper.readValue(resource.getInputStream(),
                        new TypeReference<List<GameSeedDTO>>() {});

        List<Game> games = seeds
                .stream()
                .map(this::toEntity)
                .toList();

        gameRepository.saveAll(games);
    }

    private Game toEntity(GameSeedDTO dto) {
        Set<Genre> genres = dto.getGenres().stream()
                .map(this::findOrCreateGenre)
                .collect(Collectors.toSet());

        Set<Platform> platforms = dto.getPlatforms().stream()
                .map(this::findOrCreatePlatform)
                .collect(Collectors.toSet());

        return Game.builder()
                .name(dto.getName())
                .slug(dto.getSlug())
                .developer(dto.getDeveloper())
                .publisher(dto.getPublisher())
                .releaseDate(dto.getReleaseDate())
                .description(dto.getDescription())
                .coverImage(dto.getCoverImage())
                .genres(genres)
                .platforms(platforms)
                .build();
    }

    private Genre findOrCreateGenre(String name) {
        return genreRepository.findByNameIgnoreCase(name)
                .orElseGet(() -> {
                    Genre genre = new Genre();
                    genre.setName(name);
                    return genreRepository.save(genre);
                });
    }

    private Platform findOrCreatePlatform(String name) {

        return platformRepository.findByNameIgnoreCase(name)
                .orElseGet(() -> {
                    Platform platform = new Platform();
                    platform.setName(name);
                    return platformRepository.save(platform);
                });
    }

}
