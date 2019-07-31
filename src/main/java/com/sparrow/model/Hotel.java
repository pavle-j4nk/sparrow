package com.sparrow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToOne
    private Address address;

    private String description;

    @OneToMany
    @JsonIgnore
    private Set<PriceList> priceLists;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hotel")
    private Set<Room> rooms;

    @ManyToOne
    private User admin;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private Set<HotelServices> hotelServices;

    public Hotel() {
    }

    public Hotel(String name, String description, User admin, Address address) {
        this.name = name;
        this.description = description;
        this.admin = admin;
        this.address = address;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Set<PriceList> getPriceLists() {
        return priceLists;
    }

    public void setPriceLists(Set<PriceList> priceLists) {
        this.priceLists = priceLists;
    }

    public Set<HotelServices> getHotelServices() {
        return hotelServices;
    }

    public void setHotelServices(Set<HotelServices> hotelServices) {
        this.hotelServices = hotelServices;
    }
}
