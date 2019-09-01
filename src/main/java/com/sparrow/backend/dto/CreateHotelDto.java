package com.sparrow.backend.dto;

import com.sparrow.backend.model.Address;

public class CreateHotelDto {
    private String name;

    private Address address;

    private String description;

    private String image;

    public CreateHotelDto() {

    }

    public CreateHotelDto(String name, Address address, String description, String image) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
