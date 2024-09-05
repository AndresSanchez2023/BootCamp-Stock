package com.stock.adapters.driven.jpa.mysql.adapter.factories;

import com.stock.adapters.driving.http.dto.response.BrandResponse;
import com.stock.adapters.driving.http.dto.response.CategoryResponse;
import com.stock.adapters.driving.http.dto.response.PaginatedResponse;
import com.stock.domain.util.DomainConstants;

import java.util.ArrayList;
import java.util.List;

public class PaginatedResponseFactory {

    public static PaginatedResponse<CategoryResponse> createDefaultPaginatedResponse() {
        return new PaginatedResponse<>(listOfCategoryResponse(), DomainConstants.PAGINATED_CURRENT_PAGE_DEFAULT, DomainConstants.PAGINATED_TOTAL_ITEMS_DEFAULT, DomainConstants.PAGINATED_TOTAL_PAGE_DEFAULT, DomainConstants.PAGINATION_SUCCESSFUL);
    }

    public static List<CategoryResponse> listOfCategoryResponse() {
        List<CategoryResponse> list = new ArrayList<>();
        list.add(CategoryResponseFactory.createdCategoryResponseDefault());
        return list;
    }

    //Brand
    public static PaginatedResponse<BrandResponse> createPaginatedBrandResponseDefault() {
        return new PaginatedResponse<>(List.of(BrandResponseFactory.createDefaultBrandResponse()), DomainConstants.PAGINATED_CURRENT_PAGE_DEFAULT, DomainConstants.PAGINATED_TOTAL_ITEMS_DEFAULT, DomainConstants.PAGINATED_TOTAL_PAGE_DEFAULT, DomainConstants.PAGINATION_SUCCESSFUL);
    }
}
