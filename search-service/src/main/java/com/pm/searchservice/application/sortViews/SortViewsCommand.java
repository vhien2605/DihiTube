package com.pm.searchservice.application.sortViews;

import com.pm.searchservice.domain.SortStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SortViewsCommand {
    private SortStatus sort;
    private int page = 0;
    private int pageSize = 10;
}
