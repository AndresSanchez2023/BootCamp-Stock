package com.stock.adapters.driven.jpa.mysql.adapter.factories;

import com.stock.domain.model.Pagination;
import com.stock.domain.util.DomainConstants;

public class PaginationFactory {

    public static Pagination createPaginationDefault() {
        return new Pagination(DomainConstants.PAGE_NUMBER_DEFAULT, DomainConstants.PAGE_SIZE_DEFAULT, DomainConstants.VALUE_NAME_SORT, DomainConstants.VALUE_ASC);
    }

    public static Pagination createPaginationWithPageOutOfRange() {
        return new Pagination(DomainConstants.FIELD_PAGE_OUT_RANGE, DomainConstants.PAGE_SIZE_DEFAULT, DomainConstants.VALUE_NAME_SORT, DomainConstants.VALUE_ASC);
    }

    public static Pagination createPaginationWithPageNull() {
        return new Pagination(null, DomainConstants.PAGE_SIZE_DEFAULT, DomainConstants.VALUE_NAME_SORT, DomainConstants.VALUE_ASC);
    }

    public static Pagination createPaginationWithSizeNull() {
        return new Pagination(DomainConstants.PAGE_NUMBER_DEFAULT, null, DomainConstants.VALUE_NAME_SORT, DomainConstants.VALUE_ASC);
    }

    public static Pagination createPaginationWithInvalidSortField() {
        return new Pagination(DomainConstants.PAGE_NUMBER_DEFAULT,DomainConstants.PAGE_SIZE_DEFAULT, DomainConstants.INVALID_SORT_FIELD, DomainConstants.VALUE_ASC);
    }

    public static Pagination createPaginationWithInvalidSortDirection() {
        return new Pagination(DomainConstants.PAGE_NUMBER_DEFAULT,DomainConstants.PAGE_SIZE_DEFAULT, DomainConstants.VALUE_NAME_SORT, DomainConstants.INVALID_SORT_DIRECTION);
    }
}
