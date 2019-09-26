package com.sparrow.backend.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class CarReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToMany
    private Set<Car> cars;

    private Long rentacarId;

    @ManyToOne
    private User user;

    private Date start;

    private Date end;

    private Double price;

   /* @ManyToOne
    private Dealership dealershipPickUp;

    @ManyToOne
    private Dealership dealershipDropOff;
*/
    public CarReservation(){

    }

    public CarReservation(User user, Date start, Date end) {
        this.user = user;
        this.start = start;
        this.end = end;
    }
    /*
    public Dealership getDealershipPickUp() {
        return dealershipPickUp;
    }

    public void setDealershipPickUp(Dealership dealershipPickUp) {
        this.dealershipPickUp = dealershipPickUp;
    }

    public Dealership getDealershipDropOff() {
        return dealershipDropOff;
    }

    public void setDealershipDropOff(Dealership dealershipDropOff) {
        this.dealershipDropOff = dealershipDropOff;
    }
*/


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
