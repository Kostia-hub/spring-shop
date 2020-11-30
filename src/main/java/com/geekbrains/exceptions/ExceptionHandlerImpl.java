package com.geekbrains.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerImpl {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(exception.getBindingResult().getFieldErrors().stream().map(e -> new Error(e.getDefaultMessage())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ManagerIsEarlierThanNeedException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(ManagerIsEarlierThanNeedException exception) {
        return new ResponseEntity<>(new Error(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(NotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
