package com.pm.searchservice.infra;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.analysis.TokenChar;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import com.pm.searchservice.domain.VideoDocument;
import com.pm.searchservice.domain.VideoCreateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VideoCreateImpl implements VideoCreateRepository {

    private final ElasticsearchClient client;

    @Override
    public void createIndex() {
        try {
            boolean exists = client.indices()
                    .exists(e -> e.index("videos"))
                    .value();

            if (exists) {
                return;
            }

            client.indices().create(c -> c
                    .index("videos")
                    .settings(s -> s
                            .analysis(a -> a

                                    .tokenizer("edge_ngram_tokenizer", t -> t
                                            .definition(td -> td
                                                    .edgeNgram(ng -> ng
                                                            .minGram(1)
                                                            .maxGram(20)
                                                            .tokenChars(TokenChar.Letter, TokenChar.Digit)
                                                    )
                                            )
                                    )
                                    .analyzer("autocomplete", an -> an
                                            .custom(custom -> custom
                                                    .tokenizer("edge_ngram_tokenizer")
                                                    .filter("lowercase")
                                            )
                                    )
                            )
                    )
                    .mappings(m -> m
                            .properties("id", p -> p.keyword(k -> k))
                            .properties("title", p -> p.text(t -> t
                                    // field chính → search bình thường
                                    .analyzer("standard")

                                    // sub-fields
                                    .fields("autocomplete", f -> f
                                            .text(tt -> tt
                                                    .analyzer("autocomplete")
                                                    .searchAnalyzer("standard")
                                            )
                                    )
                                    .fields("keyword", f -> f
                                            .keyword(k -> k)
                                    )
                            ))
                            .properties("description", p -> p.text(t -> t.index(false)))
                            .properties("genre", p -> p.keyword(k -> k))
                            .properties("views", p -> p.long_(l -> l))
                    )
            );

        } catch (Exception e) {
            throw new RuntimeException("Cannot create index", e);
        }
    }

    @Override
    public void createDocument(VideoDocument doc) {
        try {
            client.index(i -> i
                    .index("videos")
                    .id(doc.getId())
                    .document(doc)
            );
        } catch (Exception e) {
            throw new RuntimeException("Index failed", e);
        }
    }



}
