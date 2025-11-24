package com.nortic.pos.es.role.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public final class PaginationUtils {
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_SIZE = 10;
    private static final int MAX_PAGE_SIZE = 100;

    private PaginationUtils() {}

    /**
     * Checks if pagination should be disabled (return all results).
     * Pagination is disabled when both page and size are null or <= 0.
     *
     * @param page the page number
     * @param size the page size
     * @return true if pagination should be disabled, false otherwise
     */
    public static boolean shouldDisablePagination(Integer page, Integer size) {
        return (page == null || page <= 0) && (size == null || size <= 0);
    }

    /**
     * Creates a Pageable instance with the given page and size parameters.
     * Applies default values and validations.
     *
     * @param page the page number (1-based)
     * @param size the page size
     * @return a Pageable instance with validated page and size
     */
    public static Pageable createPageable(Integer page, Integer size) {
        int pageNumber = (page == null || page <= 0) ? DEFAULT_PAGE - 1 : page - 1;
        int pageSize = (size == null || size <= 0) ? DEFAULT_SIZE : Math.min(size, MAX_PAGE_SIZE);
        return PageRequest.of(pageNumber, pageSize);
    }
}
