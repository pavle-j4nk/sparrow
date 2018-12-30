package com.sparrow.service.impl.user.exception;

public class FriendshipDoesNotExistException extends RuntimeException {

    public FriendshipDoesNotExistException() {
    }

    public FriendshipDoesNotExistException(String s) {
        super(s);
    }
}
