package com.sparrow.service.impl.user.exception;

public class FriendshipAlreadyExistsException extends RuntimeException {

    public FriendshipAlreadyExistsException() {
    }

    public FriendshipAlreadyExistsException(String s) {
        super(s);
    }
}
