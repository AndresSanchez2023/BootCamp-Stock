package com.stock.domain.exception;

public class BrandAlreadyExistsException extends RuntimeException{

    public BrandAlreadyExistsException (String message){
        super(message);
    }
}
