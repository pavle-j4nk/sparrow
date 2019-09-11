package com.sparrow.backend.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RentacarNotFoundException extends RuntimeException {
    private Long id;

    private String name;

    public RentacarNotFoundException(){
        super();
    }

    public RentacarNotFoundException(String name){
        super(String.format("Rent-A-Car with name %s not found", name));
        this.name = name;
    }

    public RentacarNotFoundException(Long id){
        super(String.format("Rent-A-Car with id %f not found" , id));
        this.id = id;
    }
}
