package com.sparrow.service.user.exception;

import com.sparrow.service.exception.BadRequestException;

public class UserAlreadyExists extends BadRequestException {

    public UserAlreadyExists(String username) {
        super("User already exists: " + username);
    }
}
