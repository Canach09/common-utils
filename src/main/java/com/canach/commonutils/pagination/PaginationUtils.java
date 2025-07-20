package com.canach.commonutils.pagination;

public class PaginationUtils {

    public static PaginationFilters buildPaginationFilters(Integer page, Integer size, String sortBy, SortEnum sortEnum){
        final var pageNumber = page == null ? 0 : page;
        final var sizeNumber = size == null ? 50 : size;
        final var sort = sortEnum == null ? SortEnum.desc : sortEnum;
        final var sortByParam = sortBy == null || sortBy.isBlank() ? "id" : sortBy;
        return new PaginationFilters(pageNumber, sizeNumber, sortByParam, sort);
    }
}
