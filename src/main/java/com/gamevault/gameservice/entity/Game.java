package com.gamevault.gameservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String slug;

    @Column(nullable = false)
    private String developer;

    @Column(nullable = false)
    private String publisher;

    private LocalDate releaseDate;

    @Column(length = 3000)
    private String description;

    private String coverImage;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "game_platforms",
            joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "platform")
    private Set<Platform> platforms = new HashSet<>();

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }
}
