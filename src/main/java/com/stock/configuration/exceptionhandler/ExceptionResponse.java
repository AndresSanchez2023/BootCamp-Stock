package com.stock.configuration.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ExceptionResponse {

    private String message;
    private String status;
    private LocalDateTime timestamp;
}