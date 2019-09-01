package com.sparrow.backend.service.exception;

public class UserAlreadyExists extends BadRequestException {

    public UserAlreadyExists(String username) {
        super("User already exists: " + username);
    }
}
