package com.stock.adapters.driven.jpa.mysql.adapter.factories;

import com.stock.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.stock.domain.util.DomainConstants;

public class CategoryEntityFactory {
    public static CategoryEntity createDefaultCategoryEntity() {
        return new CategoryEntity(DomainConstants.DEFAULT_ID, DomainConstants.DEFAULT_NAME, DomainConstants.DEFAULT_DESCRIPTION);
    }
}
