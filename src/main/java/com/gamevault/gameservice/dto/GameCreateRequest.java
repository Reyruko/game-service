package com.gamevault.gameservice.dto;

import com.gamevault.gameservice.entity.Genre;
import com.gamevault.gameservice.entity.Platform;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameCreateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String developer;

    @NotBlank
    private String publisher;

    private LocalDate releaseDate;

    @Size(max = 3000)
    public String description;

    public String coverImage;

    @NotNull
    private Genre genre;


    private Set<Platform> platforms;
}
