package com.tzonesoft.employee.controller;

import com.tzonesoft.employee.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(value = "EmployeeController.class")
public class EmployeeHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    public ErrorResponse handleRuntime(RuntimeException runtimeException) {
        return new ErrorResponse(runtimeException.getMessage(),123);
    }
}
