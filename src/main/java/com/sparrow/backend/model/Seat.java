package com.sparrow.backend.model;

import javax.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean quickReservation;

    private Boolean available;

    @ManyToOne
    private AirplaneSegment segment;

    public Boolean getQuickReservation() {
        return quickReservation;
    }

    public void setQuickReservation(Boolean quickReservation) {
        this.quickReservation = quickReservation;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

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
