package com.gamevault.gameservice.repository;

import com.gamevault.gameservice.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {
    Optional<Genre> findByNameIgnoreCase(String name);

    boolean existsByName(String name);
}
