package com.pm.metadataservice.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Video {
    private final VideoId id;
    private String title;
    private String description;
    private List<String> genres;
    private String thumbnailUrl;
    private long duration;
    private Date releaseDate;
    private double rating;
    private long views;
    private String storageKey;
    private VideoStatus status;
    private Date createdAt;
    private Date updatedAt;

    public Video(VideoId id, String title, String description, List<String> genres,
                 String thumbnailUrl, long duration, Date releaseDate,
                 String storageKey) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.thumbnailUrl = thumbnailUrl;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.storageKey = storageKey;
        this.status = VideoStatus.PROCESSING;
        this.views = 0;
        this.createdAt = new Date();
    }

    public void increaseView() {
        this.views++;
    }
}
