package com.gamevault.gameservice.controller;

import com.gamevault.gameservice.dto.GenreCreateRequest;
import com.gamevault.gameservice.dto.GenreDTO;
import com.gamevault.gameservice.service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/games/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping()
    public GenreDTO createGenre(
            @Valid @RequestBody GenreCreateRequest request) {

        return genreService.createGenre(request);
    }
}
