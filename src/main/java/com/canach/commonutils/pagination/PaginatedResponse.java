package com.canach.commonutils.pagination;

import java.util.List;

public record PaginatedResponse<T>(
        long totalElements,
        int totalPages,
        List<T> content

) {}
