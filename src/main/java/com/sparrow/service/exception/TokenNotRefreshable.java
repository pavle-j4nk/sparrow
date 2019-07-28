package com.sparrow.service.exception;

public class TokenNotRefreshable extends BadRequestException {

    public TokenNotRefreshable(String reason) {
        super("Token not refreshable: " + reason);
    }
}
