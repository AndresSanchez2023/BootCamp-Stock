package com.stock.configuration.exceptionhandler;

import com.stock.domain.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldNameException(EmptyFieldException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryAlreadyExist(CategoryAlreadyExistsException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(ValidateSizeFieldException.class)
    public ResponseEntity<ExceptionResponse> handleExceedSizeField(ValidateSizeFieldException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(InvalidArgumentsInFieldException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidArgumentsInFieldException(InvalidArgumentsInFieldException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(BrandAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleBrandAlreadyExist(BrandAlreadyExistsException exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleBrandNotFoundException(BrandNotFoundException exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()
        ));
    }
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryNotFoundException(CategoryNotFoundException exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()
        ));
    }
    @ExceptionHandler(InvalidCategoryListException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidCategoryListException(InvalidCategoryListException exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()
        ));
    }
    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleProductAlreadyExistsException(ProductAlreadyExistsException exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()
        ));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }
}
