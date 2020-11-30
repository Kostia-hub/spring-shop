package com.geekbrains.exceptions;

import lombok.Data;
import lombok.Getter;

@Getter
public abstract class NotFoundException extends RuntimeException {
    private final String message;

    public NotFoundException(String message) {
        this.message = message;
    }
}
