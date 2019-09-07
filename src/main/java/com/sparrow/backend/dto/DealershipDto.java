package com.sparrow.backend.dto;

import com.sparrow.backend.model.Address;
import com.sparrow.backend.model.Car;
import com.sparrow.backend.model.RentACar;
import java.util.Set;

public class DealershipDto {

    private Long id;

    private String name;

    private RentACar rentACar;

    private Address address;

    private Set<Car> cars;

    public DealershipDto(Long id, String name, RentACar rentACar, Address address, Set<Car> cars) {
        this.id = id;
        this.name = name;
        this.rentACar = rentACar;
        this.address = address;
        this.cars = cars;
    }

    public DealershipDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RentACar getRentACar() {
        return rentACar;
    }

    public void setRentACar(RentACar rentACar) {
        this.rentACar = rentACar;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
