package com.sparrow.backend.service.impl.user;

public class FriendRequestAlreadyExistsException extends RuntimeException {

    public FriendRequestAlreadyExistsException() {
    }

    public FriendRequestAlreadyExistsException(String s) {
        super(s);
    }
}
