package com.gamevault.gameservice.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class GameSeedDTO {
    private String name;
    private String slug;
    private String developer;
    private String publisher;
    private LocalDate releaseDate;
    private String description;
    private String coverImage;
    private Set<String> genres;
    private Set<String> platforms;
}
