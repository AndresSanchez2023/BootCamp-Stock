package com.stock.domain.spi;

import com.stock.domain.model.Category;

public interface ICategoryPersistencePort {
    boolean existsByName(String name);

    void saveCategory(Category category);

    Long getTotalItems();
}

