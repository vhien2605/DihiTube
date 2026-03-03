package com.pm.searchservice.adapter.dto.request;

import com.pm.searchservice.domain.SortStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SortViewsRequest {
    private SortStatus sort;
    private int page = 0;
    private int pageSize = 10;
}
