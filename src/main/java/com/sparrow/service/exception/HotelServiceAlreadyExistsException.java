package com.sparrow.service.exception;

public class HotelServiceAlreadyExistsException extends BadRequestException {
    public HotelServiceAlreadyExistsException(String message) {
        super(message);
    }
}
