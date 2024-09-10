package com.stock.domain.model;

import com.stock.adapters.driven.jpa.mysql.adapter.factories.BrandFactory;
import com.stock.adapters.driven.jpa.mysql.adapter.factories.CategoryFactory;
import com.stock.adapters.driven.jpa.mysql.adapter.factories.ProductFactory;
import com.stock.domain.exception.EmptyFieldException;
import com.stock.domain.exception.InvalidArgumentsInFieldException;
import com.stock.domain.util.DomainConstants;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void shouldCreateProduct_WhenValidInputs() {
        // Arrange
        Long id = DomainConstants.DEFAULT_ID;
        String name = DomainConstants.DEFAULT_NAME;
        String description = DomainConstants.DEFAULT_DESCRIPTION;
        Long quantity = DomainConstants.DEFAULT_QUANTITY;
        BigDecimal price = DomainConstants.DEFAULT_PRICE;
        Brand brand = BrandFactory.createdBrandDefault();
        List<Category> categories = List.of(CategoryFactory.createDefaultCategory());

        // Act
        Product product = new Product(id, name, description, quantity, price, brand, categories);

        // Assert
        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(quantity, product.getQuantity());
        assertEquals(price, product.getPrice());
        assertEquals(brand, product.getBrand());
        assertEquals(categories, product.getCategoryList());
    }

    @Test
    void shouldThrowException_WhenNameIsNull() {

        // Act & Assert
        EmptyFieldException exception = assertThrows(EmptyFieldException.class, ProductFactory::createdProductWithNameNull
        );

        assertEquals(DomainConstants.FIELD_NAME_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowException_WhenNameIsBlank() {

        // Act & Assert
        EmptyFieldException exception = assertThrows(EmptyFieldException.class, ProductFactory::createdProductWithNameBlank
        );

        assertEquals(DomainConstants.FIELD_NAME_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowException_WhenDescriptionIsNull() {

        // Act & Assert
        EmptyFieldException exception = assertThrows(EmptyFieldException.class, ProductFactory::createdProductWithDescriptionNull
        );

        assertEquals(DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowException_WhenDescriptionIsBlank() {
        // Act & Assert
        EmptyFieldException exception = assertThrows(EmptyFieldException.class, ProductFactory::createdProductWithDescriptionBlank
        );

        assertEquals(DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowException_WhenQuantityIsNull() {

        // Act & Assert
        EmptyFieldException exception = assertThrows(EmptyFieldException.class, ProductFactory::createdProductWithQuantityNull
        );

        assertEquals(DomainConstants.FIELD_QUANTITY_NOTNULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowException_WhenQuantityIsZeroOrNegative() {

        // Act & Assert
        InvalidArgumentsInFieldException exception = assertThrows(InvalidArgumentsInFieldException.class, ProductFactory::createdProductWithQuantityZero
        );

        assertEquals(DomainConstants.FIELD_QUANTITY_POSITIVE_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowException_WhenPriceIsNull() {

        // Act & Assert
        EmptyFieldException exception = assertThrows(EmptyFieldException.class, ProductFactory::createdProductWithPriceNull
        );

        assertEquals(DomainConstants.FIELD_PRICE_NOTNULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowException_WhenPriceIsZeroOrNegative() {

        // Act & Assert
        InvalidArgumentsInFieldException exception = assertThrows(InvalidArgumentsInFieldException.class, ProductFactory::createdProductWithPriceZero
        );

        assertEquals(DomainConstants.FIELD_PRICE_POSITIVE_MESSAGE, exception.getMessage());
    }
}