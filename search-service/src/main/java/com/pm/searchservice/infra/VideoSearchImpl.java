package com.pm.searchservice.infra;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType;
import com.pm.searchservice.domain.VideoDocument;
import com.pm.searchservice.domain.VideoSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class VideoSearchImpl implements VideoSearchRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<VideoDocument> searchByTitle(String keyword, int page, int size) {
        try {
            BoolQuery.Builder boolQuery = new BoolQuery.Builder();

            boolQuery.must(q -> q
                    .match(m -> m
                            .field("title")
                            .query(keyword)
                            .fuzziness("AUTO")
                    ));

            NativeQuery query = NativeQuery.builder()
                    .withQuery(q -> q.bool(boolQuery.build()))
                    .withPageable(PageRequest.of(page, size))
                    .build();

            SearchHits<VideoDocument> hits =
                    elasticsearchOperations.search(query, VideoDocument.class);

            return hits.stream()
                    .map(SearchHit::getContent)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<VideoDocument> autoComplete(String keyword, int page, int size) {
        try {
            Query query = NativeQuery.builder()
                    .withQuery(q -> q
                            .match(m -> m
                                    .query(keyword)
                                    .field("title.autocomplete")
                                    .type(TextQueryType.BoolPrefix)))
                    .withPageable(PageRequest.of(0, size))
                    .build();

            SearchHits<VideoDocument> hits = elasticsearchOperations.search(query, VideoDocument.class);

            return hits.stream()
                    .map(SearchHit::getContent)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
