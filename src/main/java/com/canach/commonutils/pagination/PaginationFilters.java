package com.canach.commonutils.pagination;

public record PaginationFilters(
        int page,
        int size,
        String sortBy,
        SortEnum sort
) {
}
