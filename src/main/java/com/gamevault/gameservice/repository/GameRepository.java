package com.gamevault.gameservice.repository;

import com.gamevault.gameservice.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {

    List<Game> findTop5ByOrderByCreatedOnDesc();
    Optional<Game> findBySlug(String slug);
    boolean existsBySlug(String slug);
    boolean existsByName(String name);

}
