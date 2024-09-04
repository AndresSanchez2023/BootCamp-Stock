package com.stock.domain.model;

import com.stock.adapters.driven.jpa.mysql.adapter.factories.PaginationFactory;
import com.stock.domain.exception.InvalidArgumentsInFieldException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PaginationTest {

    @Test
    void shouldThrowExceptionWhenPageIsNull() {
        assertThrows(InvalidArgumentsInFieldException.class, PaginationFactory::createPaginationWithPageNull);
    }

    @Test
    void shouldThrowExceptionWhenSizeIsNull() {
        assertThrows(InvalidArgumentsInFieldException.class, PaginationFactory::createPaginationWithSizeNull);
    }

}