package com.sparrow.backend.model;

import javax.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AirplaneSegment segment;

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
}
