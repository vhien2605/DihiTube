package com.pm.searchservice.application.searchTitle;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SearchTitleCommand {
    private String keyword;
    private int page = 0;
    private int pageSize = 10;
}
