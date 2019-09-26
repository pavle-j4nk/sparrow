package com.sparrow.backend.dto;

import com.sparrow.backend.model.Car;
import com.sparrow.backend.model.User;
import java.sql.Date;
import java.util.Set;

public class CarReservationDto {

    private Long id;

    private Set<Car> cars;

    private String rentacarName;

    private User user;

    private Date start;

    private Date end;

    private Double price;

    public CarReservationDto(Set<Car> cars, User user, Date start, Date end, Double price) {
        this.cars = cars;
        this.user = user;
        this.start = start;
        this.end = end;
        this.price = price;
    }

    public String getRentacarName() {
        return rentacarName;
    }

    public void setRentacarName(String rentacarName) {
        this.rentacarName = rentacarName;
    }

    public CarReservationDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
