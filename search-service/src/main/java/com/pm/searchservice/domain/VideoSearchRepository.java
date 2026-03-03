package com.pm.searchservice.domain;

public interface VideoSearchRepository {
    void createIndex();
    void createDocument(VideoDocument videoDocument);
}
