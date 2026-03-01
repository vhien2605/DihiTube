package com.pm.metadataservice.infra.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "videos")
@Getter
@Setter
public class JpaVideo {
    @Id
    @NotBlank
    private String id;
    private String title;
    private String description;
    private String thumbnailUrl;
    private long duration;
    private double rating;
    private long views;
    private String storageKey;
    private String status;

    @ManyToMany
    @JoinTable(
            name = "video_genres",
            joinColumns = @JoinColumn(name = "video_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<JpaGenre> genres;
}
