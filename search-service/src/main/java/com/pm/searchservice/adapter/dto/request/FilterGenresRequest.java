package com.pm.searchservice.adapter.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FilterGenresRequest {
    private List<String> genres;
    private int page = 0;
    private int pageSize = 10;
}
