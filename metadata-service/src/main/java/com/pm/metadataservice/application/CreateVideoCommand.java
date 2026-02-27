package com.pm.metadataservice.application;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CreateVideoCommand {
    public String title;
    public String description;
    public List<String> genres;
    public String thumbnailUrl;
    public long duration;
    public Date releaseDate;
    public String storageKey;
}
