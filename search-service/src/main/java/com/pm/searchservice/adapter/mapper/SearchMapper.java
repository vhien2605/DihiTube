package com.pm.searchservice.adapter.mapper;

import com.pm.searchservice.adapter.dto.request.CreateVideoDocumentRequest;
import com.pm.searchservice.application.CreateVideoDocumentCommand;

public class SearchMapper {
    public static CreateVideoDocumentCommand toCreateVideoCommand(CreateVideoDocumentRequest req) {
        if (req == null) return null;

        return CreateVideoDocumentCommand.builder()
                .id(req.getId())
                .title(req.getTitle())
                .description(req.getDescription())
                .genre(req.getGenre())
                .views(req.getViews())
                .build();
    }
}
