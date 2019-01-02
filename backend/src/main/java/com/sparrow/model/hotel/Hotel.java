package com.sparrow.model.hotel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

//  private Address address; TODO: Create special class Address

    private String description;

//  private PriceList priceList;

    @OneToMany(mappedBy = "hotel")
    private Set<Room> rooms;

    @ManyToMany(
            fetch = FetchType.LAZY
    )
    private Set<HotelExtraService> services;


    public Hotel() {
    }

    public Hotel(String name, String description) {
        this.name = name;
        this.description = description;
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

    public Set<HotelExtraService> getServices() {
        return services;
    }

    public void setServices(Set<HotelExtraService> services) {
        this.services = services;
    }
}
