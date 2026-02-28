package com.pm.metadataservice.adapter.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class CreateVideoRequest {
    @NotBlank(message = "Title is not blank")
    private String title;

    private String description;

    @NotEmpty(message = "Genre list must not be empty")
    @Size(min = 1, message = "At least 1 genre required")
    private List<String> genreIds;

    @NotBlank(message = "Thumbnail is not blank")
    private String thumbnailUrl;

    @Positive(message = "Duration must > 0")
    private long duration;


    private Date releaseDate;

    @NotBlank(message = "Storage key is not blank")
    private String storageKey;
}
