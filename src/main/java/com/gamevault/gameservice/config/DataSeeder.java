package com.gamevault.gameservice.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamevault.gameservice.dto.GameSeedDTO;
import com.gamevault.gameservice.entity.Game;
import com.gamevault.gameservice.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

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
        return Game.builder()
                .name(dto.getName())
                .slug(dto.getSlug())
                .developer(dto.getDeveloper())
                .publisher(dto.getPublisher())
                .releaseDate(dto.getReleaseDate())
                .description(dto.getDescription())
                .coverImage(dto.getCoverImage())
                .genre(dto.getGenre())
                .platforms(dto.getPlatforms())
                .build();
    }

}
