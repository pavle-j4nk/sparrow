package com.sparrow.backend.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class HotelReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private Set<Room> rooms;

    private Date start;

    private Date end;

    @ManyToMany
    private Set<HotelServices> hotelServices;

    private Double price;

    public HotelReservation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Set<HotelServices> getHotelServices() {
        return hotelServices;
    }

    public void setHotelServices(Set<HotelServices> hotelServices) {
        this.hotelServices = hotelServices;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
