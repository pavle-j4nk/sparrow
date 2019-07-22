package com.sparrow.service.exception;

import com.sparrow.service.exception.BadRequestException;

public class UserAlreadyExists extends BadRequestException {

    public UserAlreadyExists(String username) {
        super("User already exists: " + username);
    }
}
