package com.ideas2it.ems.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class handle the custom exception
 * @author Gokul
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchFieldException.class)
    public ResponseEntity<String> handleResourceNotFound(NoSuchFieldException noSuchFieldException) {
        return new ResponseEntity<>(noSuchFieldException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(MyException.class)
    public ResponseEntity<String> handleAlreadyPresent(MyException myException) {
        return new ResponseEntity<>(myException.getMessage(), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return new ResponseEntity<>(methodArgumentNotValidException.getMessage(), HttpStatus.CONFLICT);
    }
}
