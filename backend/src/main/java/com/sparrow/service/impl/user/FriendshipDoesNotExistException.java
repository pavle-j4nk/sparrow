package com.sparrow.service.impl.user;

public class FriendshipDoesNotExistException extends RuntimeException {

    public FriendshipDoesNotExistException() {
    }

    public FriendshipDoesNotExistException(String s) {
        super(s);
    }
}
