package com.sparrow.backend.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class CarSale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    private Car car;

    private Date start;

    private Date end;

    private Double price;

    public CarSale(Car car, Date start, Date end, Double price) {
        this.car = car;
        this.start = start;
        this.end = end;
        this.price = price;
    }

    public CarSale() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date saleStart) {
        this.start = saleStart;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date saleEnd) {
        this.end = saleEnd;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
