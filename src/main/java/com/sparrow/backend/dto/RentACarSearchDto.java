package com.sparrow.backend.dto;

public class RentACarSearchDto {

    private String city;

    public RentACarSearchDto() {
    }

    public RentACarSearchDto(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
