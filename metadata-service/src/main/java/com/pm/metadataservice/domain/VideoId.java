package com.pm.metadataservice.domain;

import com.fasterxml.uuid.Generators;
import lombok.Getter;

import java.util.UUID;

@Getter
public class VideoId {
    private final UUID value;

    public VideoId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("VideoId must not be null");
        }
        this.value = value;
    }

    public static VideoId generate() {
        //uuidv6
        return new VideoId(Generators.timeBasedReorderedGenerator().generate());
    }
    public static VideoId of(UUID value) {
        return new VideoId(value);
    }

    public static VideoId of(String value) {
        return new VideoId(UUID.fromString(value));
    }

}
