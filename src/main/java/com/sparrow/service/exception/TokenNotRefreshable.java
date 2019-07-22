package com.sparrow.service.exception;

import com.sparrow.service.exception.BadRequestException;

public class TokenNotRefreshable extends BadRequestException {

    public TokenNotRefreshable(String reason) {
        super("Token not refreshable: " + reason);
    }
}
