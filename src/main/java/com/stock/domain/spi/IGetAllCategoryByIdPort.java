package com.stock.domain.spi;

import com.stock.domain.model.Category;

import java.util.List;

public interface IGetAllCategoryByIdPort {

    List<Category> getAllCategoryById(List<Long> listId);
}
