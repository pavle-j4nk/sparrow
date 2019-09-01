package com.sparrow.backend.service.impl.user;

public class FriendRequestDoesNotExistException extends RuntimeException {

    public FriendRequestDoesNotExistException(String s) {
        super(s);
    }
}
