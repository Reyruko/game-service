package com.gamevault.gameservice.service;

import com.gamevault.gameservice.dto.GenreCreateRequest;
import com.gamevault.gameservice.dto.GenreDTO;
import com.gamevault.gameservice.entity.Genre;
import com.gamevault.gameservice.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreDTO createGenre(GenreCreateRequest request) {

        Genre genre = new Genre();
        genre.setName(request.getName().toUpperCase());

        Genre savedGenre = genreRepository.save(genre);

        return new GenreDTO(
                savedGenre.getId(),
                savedGenre.getName()
        );

    }
}
