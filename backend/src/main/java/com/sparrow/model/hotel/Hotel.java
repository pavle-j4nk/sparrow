package com.sparrow.model.hotel;

import com.sparrow.model.Address;
import com.sparrow.model.user.User;

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
    private Set<PriceList> priceLists;

    @OneToMany(mappedBy = "hotel")
    private Set<Room> rooms;

    @ManyToOne
    private User admin;

    public Hotel() {
    }

    public Hotel(String name, String description, User admin) {
        this.name = name;
        this.description = description;
        this.admin = admin;
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

    //    public Set<ExtraService> getServices() {
//        return services;
//    }
//
//    public void setServices(Set<ExtraService> services) {
//        this.services = services;
//    }
}
