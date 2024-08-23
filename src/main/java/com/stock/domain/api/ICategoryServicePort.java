package com.stock.domain.api;

import com.stock.domain.model.Category;

public interface ICategoryServicePort {
    void saveCategory(Category category);
}
