package com.geekbrains.exceptions;

public class UnknownUserTypeException extends RuntimeException{
    private String message;

    public UnknownUserTypeException() {
        this.message = "Неизвестный тип пользователя.";
    }

    public UnknownUserTypeException(String message) {
        this.message = message;
    }
}
