package com.stock.domain.api;

import com.stock.domain.model.Category;
import com.stock.domain.model.Paginated;
import com.stock.domain.model.Pagination;


public interface ICategoryServicePort {
    void saveCategory(Category category);

    Paginated<Category> paginate(Pagination pagination);

    Long getTotalItems();
}
