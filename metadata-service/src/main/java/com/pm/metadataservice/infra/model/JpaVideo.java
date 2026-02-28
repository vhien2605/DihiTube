package com.pm.metadataservice.infra.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "videos")
@Getter
@Setter
public class JpaVideo {
    @Id
    private String id;
    private String title;
    private String description;
    private String thumbnailUrl;
    private long duration;
    private double rating;
    private long views;
    private String storageKey;
    private String status;
}
