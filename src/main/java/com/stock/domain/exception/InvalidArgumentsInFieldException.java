package com.stock.domain.exception;

public class InvalidArgumentsInFieldException extends RuntimeException {
    public InvalidArgumentsInFieldException(String message) {
        super(message);
    }
}
