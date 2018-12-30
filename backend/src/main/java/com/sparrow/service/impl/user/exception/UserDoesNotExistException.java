package com.sparrow.service.impl.user.exception;

public class UserDoesNotExistException extends RuntimeException {

    private String identifier;

    public UserDoesNotExistException(String identifier) {
        super("User does not exist: " + identifier);
        this.identifier = identifier;
    }

}
