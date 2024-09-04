package com.stock.adapters.driven.jpa.mysql.adapter.factories;

import com.stock.adapters.driving.http.dto.response.CategoryResponse;
import com.stock.domain.util.DomainConstants;

public class CategoryResponseFactory {

    public static CategoryResponse createdCategoryResponseDefault() {
        return new CategoryResponse(DomainConstants.DEFAULT_ID, DomainConstants.DEFAULT_NAME, DomainConstants.DEFAULT_DESCRIPTION);
    }
}
