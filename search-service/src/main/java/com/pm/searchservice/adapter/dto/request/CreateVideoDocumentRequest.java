package com.pm.searchservice.adapter.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateVideoDocumentRequest {
    private String id;
    private String title;
    private String description;
    private List<String> genre;
    private Long views;
    private String thumbnailUrl;
}
