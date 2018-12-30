package com.sparrow.service.impl.user.exception;

public class FriendRequestAlreadyExistsException extends RuntimeException {

    public FriendRequestAlreadyExistsException() {
    }

    public FriendRequestAlreadyExistsException(String s) {
        super(s);
    }
}
