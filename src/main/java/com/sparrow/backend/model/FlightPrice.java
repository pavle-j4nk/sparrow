package com.sparrow.backend.model;

import javax.persistence.*;

@Entity
public class FlightPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AirplaneSegment segment;

    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AirplaneSegment getSegment() {
        return segment;
    }

    public void setSegment(AirplaneSegment segment) {
        this.segment = segment;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
