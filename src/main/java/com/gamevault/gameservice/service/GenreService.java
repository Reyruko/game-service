package com.gamevault.gameservice.service;

import com.gamevault.gameservice.dto.GenreCreateRequest;
import com.gamevault.gameservice.dto.GenreDTO;
import com.gamevault.gameservice.entity.Genre;
import com.gamevault.gameservice.exception.GenreAlreadyExistsException;
import com.gamevault.gameservice.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreDTO createGenre(GenreCreateRequest request) {

        Genre genre = new Genre();

        if (genreRepository.existsByName(genre.getName())) {
            throw new GenreAlreadyExistsException(request.getName());
        }

        genre.setName(request.getName().trim().toLowerCase());

        Genre savedGenre = genreRepository.save(genre);

        return new GenreDTO(
                savedGenre.getId(),
                savedGenre.getName()
        );

    }
}
