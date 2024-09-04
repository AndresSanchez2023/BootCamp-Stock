package com.stock.adapters.driven.jpa.mysql.adapter.factories;

import com.stock.domain.model.Paginated;
import com.stock.domain.util.DomainConstants;

import java.util.List;

public class PaginatedFactory {
    public static <T> Paginated<T> createPaginatedWithEntities(List<T> entities) {
        return new Paginated<>(entities, DomainConstants.PAGINATED_CURRENT_PAGE_DEFAULT, DomainConstants.PAGINATED_TOTAL_ITEMS_DEFAULT, DomainConstants.PAGINATED_TOTAL_PAGE_DEFAULT, DomainConstants.PAGINATION_SUCCESSFUL);
    }
}
