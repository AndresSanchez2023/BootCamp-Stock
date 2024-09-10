package com.stock.domain.api.usecase;

import com.stock.adapters.driven.jpa.mysql.adapter.factories.BrandFactory;
import com.stock.adapters.driven.jpa.mysql.adapter.factories.CategoryFactory;
import com.stock.adapters.driven.jpa.mysql.adapter.factories.ProductFactory;
import com.stock.domain.exception.*;
import com.stock.domain.model.Brand;
import com.stock.domain.model.Category;
import com.stock.domain.model.Product;
import com.stock.domain.spi.IGetAllCategoryByIdPort;
import com.stock.domain.spi.IGetBrandByIdPort;
import com.stock.domain.spi.IProductPersistencePort;
import com.stock.domain.util.DomainConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductUseCaseTest {

    @Mock
    private IProductPersistencePort productPersistencePort;

    @Mock
    private IGetBrandByIdPort getBrandByIdPort;

    @Mock
    private IGetAllCategoryByIdPort getAllCategoryById;

    @InjectMocks
    private ProductUseCase productUseCase;

    private Long brandId;
    private Brand brand;
    private List<Long> categoryIds;
    private List<Category> categories;
    private Product product;

    @BeforeEach
    void setUp() {
        brandId = DomainConstants.DEFAULT_ID;
        brand = BrandFactory.createdBrandDefault();
        categoryIds = Arrays.asList(DomainConstants.DEFAULT_ID, DomainConstants.DEFAULT_2L);
        categories = Arrays.asList(CategoryFactory.createDefaultCategory(), CategoryFactory.createCategory(DomainConstants.DEFAULT_2L, DomainConstants.DEFAULT_NAME, DomainConstants.DEFAULT_DESCRIPTION));
        product = ProductFactory.createdProductDefault();
    }

    @Test
    void saveProduct_ShouldSaveProduct_WhenValidInputs() {
        // Arrange
        when(productPersistencePort.existsByName(product.getName())).thenReturn(false);
        when(getBrandByIdPort.findById(brandId)).thenReturn(Optional.of(brand));
        when(getAllCategoryById.getAllCategoryById(categoryIds)).thenReturn(categories);

        // Act
        productUseCase.saveProduct(product, brandId, categoryIds);

        // Assert
        assertEquals(brand, product.getBrand());
        assertEquals(categories, product.getCategoryList());
        verify(productPersistencePort).saveProduct(product);
    }


    @Test
    void saveProduct_ShouldThrowException_WhenProductNameExists() {
        // Arrange
        when(productPersistencePort.existsByName(product.getName())).thenReturn(true);

        // Act & Assert
        ProductAlreadyExistsException exception = assertThrows(ProductAlreadyExistsException.class,
                this::saveProductAndExpectException);

        assertEquals(DomainConstants.PRODUCT_ALREADY_EXISTS_EXCEPTION, exception.getMessage());
        verify(productPersistencePort, never()).saveProduct(any());
    }

    @Test
    void saveProduct_ShouldThrowException_WhenBrandNotFound() {
        // Arrange
        when(productPersistencePort.existsByName(product.getName())).thenReturn(false);
        when(getBrandByIdPort.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        BrandNotFoundException exception = assertThrows(BrandNotFoundException.class,
                this::saveProductAndExpectException);

        assertEquals(DomainConstants.BRAND_NOT_FOUND_EXCEPTION_MESSAGE, exception.getMessage());
        verify(productPersistencePort, never()).saveProduct(any());
    }

    @Test
    void saveProduct_ShouldThrowException_WhenCategoryListIsEmpty() {
        // Arrange
        when(productPersistencePort.existsByName(product.getName())).thenReturn(false);
        when(getBrandByIdPort.findById(brandId)).thenReturn(Optional.of(brand));

        // Act & Assert
        EmptyFieldException exception = assertThrows(EmptyFieldException.class,
                this::saveProductAndExpectEmptyFieldException);

        assertEquals(DomainConstants.LIST_CATEGORY_IDS_IS_EMPTY, exception.getMessage());
        verify(productPersistencePort, never()).saveProduct(any());
    }

    @Test
    void saveProduct_ShouldThrowException_WhenCategoryListExceedsMaxSize() {
        // Arrange
        List<Long> categoryIds = Arrays.asList(DomainConstants.DEFAULT_ID, DomainConstants.DEFAULT_2L, DomainConstants.DEFAULT_3L, DomainConstants.DEFAULT_4L);
        when(productPersistencePort.existsByName(product.getName())).thenReturn(false);
        when(getBrandByIdPort.findById(brandId)).thenReturn(Optional.of(brand));

        // Act & Assert
        ValidateSizeFieldException exception = assertThrows(ValidateSizeFieldException.class,
                () -> productUseCase.saveProduct(product, DomainConstants.DEFAULT_ID, categoryIds));

        assertEquals(DomainConstants.VALIDATE_LIST_ID_OF_CATEGORIES, exception.getMessage());
        verify(productPersistencePort, never()).saveProduct(any());
    }

    @Test
    void saveProduct_ShouldThrowException_WhenCategoryListContainsDuplicates() {
        // Arrange
        List<Long> categoryIds = Arrays.asList(DomainConstants.DEFAULT_ID, DomainConstants.DEFAULT_2L, DomainConstants.DEFAULT_2L);
        when(productPersistencePort.existsByName(product.getName())).thenReturn(false);
        when(getBrandByIdPort.findById(brandId)).thenReturn(Optional.of(brand));

        // Act & Assert
        InvalidCategoryListException exception = assertThrows(InvalidCategoryListException.class,
                () -> productUseCase.saveProduct(product, DomainConstants.DEFAULT_ID, categoryIds));

        assertEquals(DomainConstants.CATEGORY_LIST_CONTAINS_DUPLICATE_IDS, exception.getMessage());
        verify(productPersistencePort, never()).saveProduct(any());
    }

    @Test
    void saveProduct_ShouldThrowException_WhenCategoriesNotFound() {
        // Arrange
        when(productPersistencePort.existsByName(product.getName())).thenReturn(false);
        when(getBrandByIdPort.findById(brandId)).thenReturn(Optional.of(brand));
        when(getAllCategoryById.getAllCategoryById(categoryIds)).thenReturn(Collections.singletonList(CategoryFactory.createDefaultCategory()));

        // Act & Assert
        CategoryNotFoundException exception = assertThrows(CategoryNotFoundException.class,
                () -> productUseCase.saveProduct(product, brandId, categoryIds));

        assertEquals(DomainConstants.CATEGORIES_NOT_FOUND, exception.getMessage());
        verify(productPersistencePort, never()).saveProduct(any());
    }

    private void saveProductAndExpectException() {
        productUseCase.saveProduct(product, DomainConstants.DEFAULT_ID,
                categoryIds);
    }

    private void saveProductAndExpectEmptyFieldException() {
        List<Long> listEmpty = List.of();
        productUseCase.saveProduct(product, brandId,
                listEmpty);
    }

}