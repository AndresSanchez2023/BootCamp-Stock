package com.stock.adapters.driven.jpa.mysql.adapter;

import com.stock.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.stock.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.stock.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.stock.domain.model.Category;
import com.stock.adapters.driven.jpa.mysql.adapter.factories.CategoryFactory;
import com.stock.domain.util.DomainConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private ICategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryAdapter categoryAdapter;

    @Test
    void existsByName_ShouldReturnTrue_WhenCategoryExists() {
        when(categoryRepository.existsByName(anyString())).thenReturn(true);

        boolean exists = categoryAdapter.existsByName(DomainConstants.DEFAULT_NAME);

        assertTrue(exists);
        verify(categoryRepository, times(1)).existsByName(anyString());
    }

    @Test
    void saveCategory_ShouldCallSaveOnRepository() {

        Category category = CategoryFactory.createDefaultCategory();
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(DomainConstants.DEFAULT_ID);
        categoryEntity.setName(DomainConstants.DEFAULT_NAME);
        categoryEntity.setDescription(DomainConstants.DEFAULT_DESCRIPTION);

        when(categoryEntityMapper.toEntity(any(Category.class))).thenReturn(categoryEntity);

        categoryAdapter.saveCategory(category);

        verify(categoryRepository, times(1)).save(any(CategoryEntity.class));
        verify(categoryEntityMapper, times(1)).toEntity(category);
    }
}