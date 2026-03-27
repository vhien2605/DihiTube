package com.pm.searchservice.adapter.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchTitleRequest {
    private String keyword;
    private int page = 0;
    private int pageSize = 10;
}
