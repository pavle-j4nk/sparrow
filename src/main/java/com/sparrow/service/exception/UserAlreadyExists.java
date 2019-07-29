package com.sparrow.service.exception;

public class UserAlreadyExists extends BadRequestException {

    public UserAlreadyExists(String username) {
        super("User already exists: " + username);
    }
}
