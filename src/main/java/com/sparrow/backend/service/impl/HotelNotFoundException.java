package com.sparrow.backend.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HotelNotFoundException extends RuntimeException {
    private Long id;

    private String name;

    public HotelNotFoundException() {
        super();
    }

    public HotelNotFoundException(String name) {
        super(String.format("Hotel with name: %s not found", name));
        this.name = name;
    }

    public HotelNotFoundException(Long id) {
        super(String.format("Hotel with id: %d not found", id));
        this.id = id;
    }
}
