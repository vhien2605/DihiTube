package com.pm.metadataservice.adapter.mapper;

import com.pm.metadataservice.adapter.dto.request.CreateVideoRequest;
import com.pm.metadataservice.application.CreateVideoCommand;
import com.pm.metadataservice.domain.Video;

public class VideoMapper {
    public static CreateVideoCommand toCreateVideoCommand(CreateVideoRequest request) {
        if (request == null) return null;

        CreateVideoCommand cmd = new CreateVideoCommand();
        cmd.title = request.getTitle();
        cmd.description = request.getDescription();
        cmd.genres = request.getGenres();
        cmd.thumbnailUrl = request.getThumbnailUrl();
        cmd.duration = request.getDuration();
        cmd.releaseDate = request.getReleaseDate();
        cmd.storageKey = request.getStorageKey();

        return cmd;
    }
}
