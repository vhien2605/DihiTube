package com.pm.searchservice.application.filerGenres;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class FilterGenresCommand {
    private List<String> genres;
    private int page = 0;
    private int pageSize = 10;
}
