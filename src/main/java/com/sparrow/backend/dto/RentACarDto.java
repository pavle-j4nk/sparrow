package com.sparrow.backend.dto;

import com.sparrow.backend.model.Address;
import com.sparrow.backend.model.Car;
import com.sparrow.backend.model.Dealership;
import com.sparrow.backend.model.User;
import java.util.Set;

public class RentACarDto {
    private long id ;

    private String name;

    private Address address;

    private String description;

    private Set<Car> cars;

    private Set<Dealership> dealerships;

    private User admin;

    public RentACarDto(long id, String name, Address address, String description, Set<Car> cars, Set<Dealership> dealerships, User admin) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.cars = cars;
        this.dealerships = dealerships;
        this.admin = admin;
    }

    public RentACarDto(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Set<Dealership> getDealerships() {
        return dealerships;
    }

    public void setDealerships(Set<Dealership> dealerships) {
        this.dealerships = dealerships;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
}
