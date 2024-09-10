package com.stock.domain.api.usecase;


import com.stock.adapters.driven.jpa.mysql.adapter.factories.PaginationFactory;
import com.stock.domain.exception.BrandAlreadyExistsException;
import com.stock.domain.exception.InvalidArgumentsInFieldException;
import com.stock.domain.model.Brand;
import com.stock.adapters.driven.jpa.mysql.adapter.factories.BrandFactory;
import com.stock.domain.model.Paginated;
import com.stock.domain.model.Pagination;
import com.stock.domain.spi.IBrandPersistencePort;
import com.stock.domain.spi.IPaginationPort;
import com.stock.domain.util.DomainConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;
class BrandUseCaseTest {
    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @Mock
    private IPaginationPort<Brand> brandPaginationPort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void When_BrandDoesNotExist_Expect_SaveBrand() {
        // Arrange
        Brand brand = BrandFactory.createdBrandDefault();
        when(brandPersistencePort.existsByName(brand.getName())).thenReturn(false);

        // Act
        brandUseCase.saveBrand(brand);

        // Assert
        verify(brandPersistencePort).saveBrand(brand);
    }

    @Test
    void When_BrandExists_Expect_ThrowBrandAlreadyExistsException() {
        // Arrange
        Brand brand = BrandFactory.createdBrandDefault();
        when(brandPersistencePort.existsByName(brand.getName())).thenReturn(true);

        // Act & Assert
        assertThrows(BrandAlreadyExistsException.class, () -> brandUseCase.saveBrand(brand));
    }

    @Test
    void whenPaginatingEmptyList_thenReturnEmptyPaginatedWithMessage() {
        // Arrange
        Pagination pagination = PaginationFactory.createPaginationDefault();
        when(brandPaginationPort.paginate(pagination)).thenReturn(PaginatedFactory.createdPaginatedBrandWithListEmpty());
        when(brandPersistencePort.getTotalItems()).thenReturn(DomainConstants.LIST_COUNT_IS_EMPTY_LONG);

        // Act
        Paginated<Brand> result = brandUseCase.paginate(pagination);

        // Assert
        assertTrue(DomainConstants.LIST_TO_BE_EMPTY, result.getEntities().isEmpty());
        assertEquals(DomainConstants.MESSAGE_NO_CATEGORIES_REGISTERED, result.getMessage());
    }

    @Test
    void whenPaginateWithOutOfRangePage_thenReturnEmptyWithMessage() {
        // Arrange
        Pagination pagination = PaginationFactory.createPaginationWithPageOutOfRange();
        when(brandPersistencePort.getTotalItems()).thenReturn(DomainConstants.LIST_COUNT_NOT_EMPTY_LONG);
        when(brandPaginationPort.paginate(pagination)).thenReturn(PaginatedFactory.createPaginatedBrandOutOfRange());

        // Act
        Paginated<Brand> result = brandUseCase.paginate(pagination);

        // Assert
        assertTrue(DomainConstants.MESSAGE_PAGE_OUT_OF_RANGE, result.getEntities().isEmpty());
        assertTrue(DomainConstants.LIST_TO_BE_EMPTY, result.getMessage().contains(DomainConstants.MESSAGE_PAGE_OUT_OF_RANGE));
    }

    @Test
    void whenPaginateWithValidPage_thenReturnPaginatedSuccessfully() {
        // Arrange
        Pagination pagination = PaginationFactory.createPaginationDefault();
        Paginated<Brand> paginated = PaginatedFactory.createdPaginatedBrandDefault();
        when(brandPersistencePort.getTotalItems()).thenReturn(DomainConstants.TOTAL_ITEMS_PAGINATED_LONG);
        when(brandPaginationPort.paginate(pagination)).thenReturn(paginated);

        // Act
        Paginated<Brand> result = brandUseCase.paginate(pagination);

        // Assert
        assertFalse(DomainConstants.LIST_TO_NOT_BE_EMPTY, result.getEntities().isEmpty());
        assertEquals(paginated.getTotalItems(), result.getTotalItems());
        assertEquals(paginated.getCurrentPage(), result.getCurrentPage());
    }

    @Test
    void whenInvalidSortField_thenThrowInvalidArgumentsInFieldException() {
        // Arrange
        Pagination pagination = PaginationFactory.createPaginationWithInvalidSortField();
        when(brandPersistencePort.getTotalItems()).thenReturn(DomainConstants.TOTAL_ITEMS_PAGINATED_LONG);

        // Act & Assert
        assertThrows(InvalidArgumentsInFieldException.class, () -> brandUseCase.paginate(pagination));
    }

    @Test
    void whenInvalidSortDirection_thenThrowInvalidArgumentsInFieldException() {
        // Arrange
        Pagination pagination = PaginationFactory.createPaginationWithInvalidSortDirection();
        when(brandPersistencePort.getTotalItems()).thenReturn(DomainConstants.TOTAL_ITEMS_PAGINATED_LONG);

        // Act & Assert
        assertThrows(InvalidArgumentsInFieldException.class, () -> brandUseCase.paginate(pagination));
    }
}

