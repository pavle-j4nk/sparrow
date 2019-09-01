package com.sparrow.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Airline airline;

    @ManyToOne
    private Destination from;

    @ManyToOne
    private Destination to;

    private LocalDateTime departure;

    private LocalDateTime arrival;

    @OneToMany
    private List<Destination> changeovers;

    private Integer duration;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AirplaneSegment> airplaneSegments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Destination getFrom() {
        return from;
    }

    public void setFrom(Destination from) {
        this.from = from;
    }

    public Destination getTo() {
        return to;
    }

    public void setTo(Destination to) {
        this.to = to;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public List<AirplaneSegment> getAirplaneSegments() {
        return airplaneSegments;
    }

    public void setAirplaneSegments(List<AirplaneSegment> airplaneSegments) {
        this.airplaneSegments = airplaneSegments;
    }

    public List<Destination> getChangeovers() {
        return changeovers;
    }

    public void setChangeovers(List<Destination> changeovers) {
        this.changeovers = changeovers;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
