package com.stock.domain.model;

import com.stock.domain.exception.InvalidArgumentsInFieldException;
import com.stock.domain.util.DomainConstants;

public class Pagination {

    private final Integer page;
    private final Integer size;
    private final String sort;
    private final String sortDirection;

    public Pagination(Integer page, Integer size, String sort, String sortDirection) {
        validate(page, size);
        this.page = page;
        this.size = size;
        this.sort = sort;
        this.sortDirection = sortDirection;
    }

    private void validate(Integer page, Integer size) {
        if (page == null || page < 0 || page > 50) {
            throw new InvalidArgumentsInFieldException(DomainConstants.FIELD_PAGE_VALIDATE_LENGTH_EXCEPTION_MESSAGE);
        }
        if (size == null || size < 1 || size > 20) {
            throw new InvalidArgumentsInFieldException(DomainConstants.FIELD_SIZE_VALIDATE_LENGTH_EXCEPTION_MESSAGE);
        }
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    public String getSort() {
        return sort;
    }

    public String getSortDirection() {
        return sortDirection;
    }
}
