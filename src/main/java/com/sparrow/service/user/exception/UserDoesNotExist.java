package com.sparrow.service.user.exception;

import com.sparrow.service.exception.BadRequestException;

public class UserDoesNotExist extends BadRequestException {
    public UserDoesNotExist(String email) {
        super("User does not exist: " + email);
    }

}
