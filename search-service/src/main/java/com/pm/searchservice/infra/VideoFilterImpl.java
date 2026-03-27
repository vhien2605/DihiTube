package com.pm.searchservice.infra;

import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import com.pm.searchservice.domain.SortStatus;
import com.pm.searchservice.domain.VideoDocument;
import com.pm.searchservice.domain.VideoFilterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VideoFilterImpl implements VideoFilterRepository {

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
    public List<VideoDocument> autoComplete(String keyword, int size) {
        try {
            Query query = NativeQuery.builder()
                    .withQuery(q -> q
                            .match(m -> m
                                    .field("title.autocomplete")
                                    .query(keyword)
                            )
                    )
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

    @Override
    public List<VideoDocument> filterByGenres(List<String> genres, int page, int size) {
        try {
            if (genres == null || genres.isEmpty()) {
                return List.of();
            }

            Query query = NativeQuery.builder()
                    .withQuery(q -> q
                            .bool(b -> {
                                genres.forEach(g ->
                                        b.must(m -> m
                                                .term(t -> t
                                                        .field("genre")
                                                        .value(g)
                                                )
                                        )
                                );
                                return b;
                            })
                    )
                    .withPageable(PageRequest.of(page, size))
                    .build();

            SearchHits<VideoDocument> hits =
                    elasticsearchOperations.search(query, VideoDocument.class);

            return hits.getSearchHits()
                    .stream()
                    .map(SearchHit::getContent)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<VideoDocument> sortByViews(SortStatus sort, int page, int size) {
        try {
            SortOrder order = (sort == SortStatus.DESC)
                    ? SortOrder.Desc
                    : SortOrder.Asc;

            Query query = NativeQuery.builder()
                    .withQuery(q -> q.matchAll(m -> m))
                    .withSort(s -> s
                            .field(f -> f
                                    .field("views")
                                    .order(order)
                            )
                    )
                    .withPageable(PageRequest.of(page, size))
                    .build();

            SearchHits<VideoDocument> hits =
                    elasticsearchOperations.search(query, VideoDocument.class);

            return hits.getSearchHits()
                    .stream()
                    .map(SearchHit::getContent)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
