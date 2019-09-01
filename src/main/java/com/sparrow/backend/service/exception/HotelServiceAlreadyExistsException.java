package com.sparrow.backend.service.exception;

public class HotelServiceAlreadyExistsException extends BadRequestException {
    public HotelServiceAlreadyExistsException(String message) {
        super(message);
    }
}
