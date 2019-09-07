package com.sparrow.backend.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class RentACar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String name;

    @OneToOne
    private Address address;

    @Column
    private String description;

   // private Set<PriceList> priceLists;

    @OneToMany
    private Set<Car> cars;

    @OneToMany
    private Set<Dealership> dealerships;

    @ManyToOne
    private User admin;

    public RentACar(){

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

    public void setAddress(Address adress) {
        this.address = adress;
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
