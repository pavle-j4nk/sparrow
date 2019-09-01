package com.sparrow.backend.service.exception;

public class TokenNotRefreshable extends BadRequestException {

    public TokenNotRefreshable(String reason) {
        super("Token not refreshable: " + reason);
    }
}
