package com.stock.domain.usecase;

import com.stock.domain.api.usecase.CategoryUseCase;
import com.stock.domain.exception.CategoryAlreadyExistsException;
import com.stock.domain.model.Category;
import com.stock.domain.model.CategoryFactory;
import com.stock.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


class CategoryUseCaseTest {
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldThrowExceptionWhenCategoryAlreadyExists() {

        Category category = CategoryFactory.createDefaultCategory();
        when(categoryPersistencePort.existsByName(category.getName())).thenReturn(true);

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    void shouldSaveCategoryWhenCategoryDoesNotExist() {

        Category category = CategoryFactory.createDefaultCategory();
        when(categoryPersistencePort.existsByName(category.getName())).thenReturn(false);

        categoryUseCase.saveCategory(category);

        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }
}
