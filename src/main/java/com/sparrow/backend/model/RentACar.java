package com.sparrow.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "rentacar")
public class RentACar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String name;

    @OneToOne
    private Address address;

    @Column
    private String description;

    @OneToMany(mappedBy = "rentACar", fetch = FetchType.EAGER)
    private Set<Car> cars;

    @OneToMany(mappedBy = "rentACar" , fetch = FetchType.EAGER)
    private Set<Dealership> dealerships;

    @ManyToOne
    private User admin;

    private  String city;

    public RentACar(){

    }

    public RentACar(String name, String description, User admin , Address address , String city) {
        this.name = name;
        this.description = description;
        this.admin = admin;
        this.address = address;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
