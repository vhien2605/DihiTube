package com.pm.metadataservice.application;

import com.pm.metadataservice.domain.GenreId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CreateVideoCommand {
    public String title;
    public String description;
    public List<String> genreIds;
    public String thumbnailUrl;
    public long duration;
    public Date releaseDate;
    public String storageKey;
}
