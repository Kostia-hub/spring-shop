package com.geekbrains.exceptions;

import org.aspectj.weaver.ast.Not;

public class RoleNotFoundException extends NotFoundException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
