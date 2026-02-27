package com.pm.metadataservice.adapter.dto.request;

import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class CreateVideoRequest {
    private String title;
    private String description;
    private List<String> genres;
    private String thumbnailUrl;
    private long duration;
    private Date releaseDate;
    private String storageKey;
}
