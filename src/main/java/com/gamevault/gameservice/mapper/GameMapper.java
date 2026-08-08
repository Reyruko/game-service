package com.gamevault.gameservice.mapper;

import com.gamevault.gameservice.dto.GameCreateRequest;
import com.gamevault.gameservice.dto.GameDTO;
import com.gamevault.gameservice.dto.GenreDTO;
import com.gamevault.gameservice.dto.PlatformDTO;
import com.gamevault.gameservice.entity.Game;
import com.gamevault.gameservice.entity.Genre;
import com.gamevault.gameservice.entity.Platform;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {

    private final ModelMapper modelMapper;
    public GameMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public GameDTO toDTO(Game game) {
        return modelMapper.map(game, GameDTO.class);
    }

    public Game toEntity(GameCreateRequest request) {
        return modelMapper.map(request, Game.class);
    }

    public GenreDTO toGenreDTO(Genre genre) {
        return modelMapper.map(genre, GenreDTO.class);
    }

    public PlatformDTO toPlatformDTO(Platform platform) {
        return modelMapper.map(platform, PlatformDTO.class);
    }
}
