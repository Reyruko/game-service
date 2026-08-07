package com.gamevault.gameservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class GameUpdateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String developer;

    @NotBlank
    private String publisher;

    private LocalDate releaseDate;

    @Size(max = 3000)
    private String description;

    private String coverImage;

    private Set<UUID> genreIds;

    private Set<UUID> platformIds;
}
