package com.stock.domain.api.usecase;

import com.stock.adapters.driven.jpa.mysql.adapter.factories.PaginationFactory;
import com.stock.domain.exception.CategoryAlreadyExistsException;
import com.stock.domain.model.Category;
import com.stock.adapters.driven.jpa.mysql.adapter.factories.CategoryFactory;
import com.stock.domain.model.Paginated;
import com.stock.domain.model.Pagination;
import com.stock.domain.spi.ICategoryPersistencePort;
import com.stock.domain.spi.IPaginationPort;
import com.stock.domain.util.DomainConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

class CategoryUseCaseTest {
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @Mock
    private IPaginationPort<Category> categoryPaginationPort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenCategoryExists_thenThrowCategoryAlreadyExistsException() {

        Category category = CategoryFactory.createDefaultCategory();
        when(categoryPersistencePort.existsByName(category.getName())).thenReturn(true);

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    void whenCategoryDoesNotExist_thenSaveCategorySuccessfully() {

        Category category = CategoryFactory.createDefaultCategory();
        when(categoryPersistencePort.existsByName(category.getName())).thenReturn(false);

        categoryUseCase.saveCategory(category);

        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }


    @Test
    void whenPaginatingEmptyList_thenReturnEmptyPaginatedWithMessage() {
        // Arrange
        Pagination pagination = PaginationFactory.createPaginationDefault();
        when(categoryPaginationPort.paginate(pagination)).thenReturn(PaginatedFactory.createdPaginatedWithListEmpty());
        when(categoryPersistencePort.getTotalItems()).thenReturn(0L);

        // Act
        Paginated<Category> result = categoryUseCase.paginate(pagination);

        // Assert
        assertTrue(DomainConstants.LIST_TO_BE_EMPTY, result.getEntities().isEmpty());
        assertEquals(DomainConstants.MESSAGE_NO_CATEGORIES_REGISTERED, result.getMessage());
    }

    @Test
    void whenPaginateWithOutOfRangePage_thenReturnEmptyWithMessage() {
        // Arrange
        Pagination pagination = PaginationFactory.createPaginationWithPageOutOfRange();
        when(categoryPersistencePort.getTotalItems()).thenReturn(5L);
        when(categoryPaginationPort.paginate(pagination)).thenReturn(PaginatedFactory.createPaginatedOutOfRange());

        // Act
        Paginated<Category> result = categoryUseCase.paginate(pagination);

        // Assert
        assertTrue(DomainConstants.MESSAGE_PAGE_OUT_OF_RANGE, result.getEntities().isEmpty());
        assertTrue(DomainConstants.LIST_TO_BE_EMPTY, result.getMessage().contains(DomainConstants.MESSAGE_PAGE_OUT_OF_RANGE));
    }

    @Test
    void whenPaginateWithValidPage_thenReturnPaginatedSuccessfully() {
        // Arrange
        Pagination pagination = PaginationFactory.createPaginationDefault();
        Paginated<Category> paginated = PaginatedFactory.createdPaginatedDefault();
        when(categoryPersistencePort.getTotalItems()).thenReturn(10L);
        when(categoryPaginationPort.paginate(pagination)).thenReturn(paginated);

        // Act
        Paginated<Category> result = categoryUseCase.paginate(pagination);

        // Assert
        assertFalse(DomainConstants.LIST_TO_NOT_BE_EMPTY, result.getEntities().isEmpty());
        assertEquals(paginated.getTotalItems(), result.getTotalItems());
        assertEquals(paginated.getCurrentPage(), result.getCurrentPage());
    }

}