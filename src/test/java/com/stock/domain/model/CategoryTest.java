package com.stock.domain.model;

import com.stock.domain.exception.EmptyFieldException;
import com.stock.domain.exception.InvalidArgumentsInFieldException;
import com.stock.domain.exception.ValidateSizeFieldException;
import com.stock.domain.util.DomainConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryTest {
    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        assertThrows(NullPointerException.class, () -> CategoryFactory.createCategory(1L, null, "Valid Description"));
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        assertThrows(NullPointerException.class, () -> CategoryFactory.createCategory(1L, "Valid Name", null));
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        assertThrows(EmptyFieldException.class, () -> CategoryFactory.createCategoryWithName(""));
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty() {
        assertThrows(EmptyFieldException.class, () -> CategoryFactory.createCategoryWithDescription(""));
    }

    @Test
    void shouldThrowExceptionWhenNameExceedsMaxLength() {
        String longName = "a".repeat(DomainConstants.FIELD_NAME_MAX_MESSAGE + 1);
        assertThrows(ValidateSizeFieldException.class, () -> CategoryFactory.createCategoryWithName(longName));
    }

    @Test
    void shouldThrowExceptionWhenDescriptionExceedsMaxLength() {
        String longDescription = "a".repeat(DomainConstants.FIELD_DESCRIPTION_MAX_MESSAGE + 1);
        assertThrows(ValidateSizeFieldException.class, () -> CategoryFactory.createCategoryWithDescription(longDescription  ));
    }

    @Test
    void shouldThrowExceptionWhenNameNotMinLength() {
        String minName = "a".repeat(DomainConstants.FIELD_NAME_MIN_MESSAGE - 1);
        assertThrows(ValidateSizeFieldException.class, () -> CategoryFactory.createCategoryWithName(minName));
    }

    @Test
    void shouldThrowExceptionWhenDescriptionNotMinLength() {
        String minDescription = "a".repeat(DomainConstants.FIELD_DESCRIPTION_MIN_MESSAGE - 1);
        assertThrows(ValidateSizeFieldException.class, () -> CategoryFactory.createCategoryWithDescription(minDescription  ));
    }

    @Test
    void shouldThrowExceptionWhenNameContainsInvalidCharacters() {
        String invalidName = DomainConstants.INVALID_NAME;
        assertThrows(InvalidArgumentsInFieldException.class, () ->  CategoryFactory.createCategoryWithName(invalidName));
    }

    @Test
    void shouldThrowExceptionWhenDescriptionContainsInvalidCharacters() {
        String invalidDescription = DomainConstants.INVALID_DESCRIPTION;
        assertThrows(InvalidArgumentsInFieldException.class, () -> CategoryFactory.createCategoryWithDescription(invalidDescription));
    }
}
