package com.pm.searchservice.domain;

import java.util.List;

public interface VideoCreateRepository {
    void createIndex();
    void createDocument(VideoDocument videoDocument);
}
