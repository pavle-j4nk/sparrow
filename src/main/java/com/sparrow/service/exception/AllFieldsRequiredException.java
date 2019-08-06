package com.sparrow.service.exception;

public class AllFieldsRequiredException extends BadRequestException {
    public AllFieldsRequiredException(String message) {
        super(message);
    }
}
