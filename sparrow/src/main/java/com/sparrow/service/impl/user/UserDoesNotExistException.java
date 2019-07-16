package com.sparrow.service.impl.user;

public class UserDoesNotExistException extends RuntimeException {

    private String identifier;

    public UserDoesNotExistException(String identifier) {
        super("User does not exist: " + identifier);
        this.identifier = identifier;
    }

}
