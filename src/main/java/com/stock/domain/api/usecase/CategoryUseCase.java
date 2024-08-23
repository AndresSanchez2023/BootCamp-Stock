package com.stock.domain.api.usecase;

import com.stock.domain.api.ICategoryServicePort;
import com.stock.domain.exception.CategoryAlreadyExistsException;
import com.stock.domain.model.Category;
import com.stock.domain.spi.ICategoryPersistencePort;
import com.stock.domain.util.DomainConstants;

public class CategoryUseCase implements ICategoryServicePort {

    private ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase (ICategoryPersistencePort categoryPersistencePort){
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        boolean categoryExists = categoryPersistencePort.existsByName(category.getName());
        if(categoryExists){
            throw new CategoryAlreadyExistsException(DomainConstants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        }
        categoryPersistencePort.saveCategory(category);
    }
}
