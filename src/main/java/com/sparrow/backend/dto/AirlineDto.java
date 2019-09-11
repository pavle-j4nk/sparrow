package com.sparrow.backend.dto;

import com.sparrow.backend.model.Address;
import com.sparrow.backend.model.Airline;
import com.sparrow.backend.model.Destination;
import com.sparrow.backend.model.User;

import java.util.List;

public class AirlineDto {
    private Long id;
    private User admin;
    private String name;
    private Address address;
    private String description;
    private List<Destination> destinations;

    public AirlineDto(Airline airline) {
        this.id = airline.getId();
        this.admin = airline.getAdmin();
        this.name = airline.getName();
        this.address = airline.getAddress();
        this.description = airline.getDescription();
        this.destinations = airline.getDestinations();
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public AirlineDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
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
}
