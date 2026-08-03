package com.gamevault.gameservice.dto;

import com.gamevault.gameservice.entity.Genre;
import com.gamevault.gameservice.entity.Platform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDTO {
    private UUID id;
    private String name;
    private String slug;
    private String developer;
    private String publisher;
    private LocalDate releaseDate;
    private String description;
    private String coverImage;
    private Genre genre;
    private Set<Platform> platforms;
}
