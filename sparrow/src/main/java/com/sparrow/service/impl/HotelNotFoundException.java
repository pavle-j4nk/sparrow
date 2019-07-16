package com.sparrow.service.impl;

public class HotelNotFoundException extends RuntimeException {
    private Long id;

    private String name;

    public HotelNotFoundException(String name) {
        super(String.format("Hotel with name: %s not found", name));
        this.name = name;
    }

    public HotelNotFoundException(Long id) {
        super(String.format("Hotel with id: %f not found", id));
        this.id = id;
    }
}
