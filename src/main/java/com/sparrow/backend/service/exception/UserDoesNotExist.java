package com.sparrow.backend.service.exception;

public class UserDoesNotExist extends NotFoundException {
    public UserDoesNotExist(String email) {
        super("User does not exist: " + email);
    }
}
