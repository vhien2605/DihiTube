package com.pm.searchservice.application.createIndex;

import com.pm.searchservice.domain.VideoDocument;
import com.pm.searchservice.domain.VideoCreateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateIndexUseCase {
    private final VideoCreateRepository videoSearch;

    public void execute() {
        videoSearch.createIndex();
    }

    public void createDocument(CreateVideoDocumentCommand cmd) {
        VideoDocument doc = new VideoDocument(
                cmd.getId(),
                cmd.getTitle(),
                cmd.getDescription(),
                cmd.getGenre(),
                cmd.getViews(),
                cmd.getThumbnailUrl()
        );

        videoSearch.createDocument(doc);
    }
}
