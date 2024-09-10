package com.stock.domain.exception;

public class InvalidCategoryListException extends RuntimeException {
    public InvalidCategoryListException(String message) {
        super(message);
    }
}
