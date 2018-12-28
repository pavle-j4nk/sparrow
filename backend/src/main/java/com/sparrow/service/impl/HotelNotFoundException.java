package com.sparrow.service.impl;

public class HotelNotFoundException extends RuntimeException {
    private String name;

    public HotelNotFoundException(String name) {
        super(String.format("Hotel with name: %s not found", name));
        this.name = name;
    }
}
