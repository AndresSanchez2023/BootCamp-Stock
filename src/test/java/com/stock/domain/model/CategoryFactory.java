package com.stock.domain.model;

import com.stock.domain.util.DomainConstants;

public class CategoryFactory {


    public static Category createDefaultCategory() {
        return new Category(DomainConstants.DEFAULT_ID, DomainConstants.DEFAULT_NAME, DomainConstants.DEFAULT_DESCRIPTION);
    }

    public static Category createCategoryWithName(String name) {
        return new Category(DomainConstants.DEFAULT_ID, name, DomainConstants.DEFAULT_DESCRIPTION);
    }

    public static Category createCategoryWithDescription(String description) {
        return new Category(DomainConstants.DEFAULT_ID, DomainConstants.DEFAULT_NAME, description);
    }

    public static Category createCategory(Long id, String name, String description) {
        return new Category(id, name, description);
    }
}

