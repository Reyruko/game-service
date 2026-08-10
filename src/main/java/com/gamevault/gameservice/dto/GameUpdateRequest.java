package com.gamevault.gameservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;

    @Size(max = 3000)
    private String description;

    private Set<UUID> genreIds;

    private Set<UUID> platformIds;
}
