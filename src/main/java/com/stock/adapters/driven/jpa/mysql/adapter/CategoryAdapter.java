package com.stock.adapters.driven.jpa.mysql.adapter;

import com.stock.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.stock.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.stock.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.stock.domain.model.Category;
import com.stock.domain.spi.ICategoryPersistencePort;
import com.stock.domain.spi.IGetAllCategoryByIdPort;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort, IGetAllCategoryByIdPort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public Long getTotalItems() {
        return categoryRepository.count();
    }

    @Override
    public List<Category> getAllCategoryById(List<Long> listId) {
        List<CategoryEntity> categoryEntities = categoryRepository.findAllById(listId);
        List<Category> categoryList = new ArrayList<>();
        categoryEntities.forEach(categoryEntity ->
                categoryList.add(categoryEntityMapper.toModel(categoryEntity)));
        return categoryList;
    }
}
