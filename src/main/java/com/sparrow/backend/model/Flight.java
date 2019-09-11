package com.sparrow.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties({"flights"})
    private Airline airline;

    @ManyToOne()
    private Destination from;

    private Long price;

    @ManyToOne
    private Destination to;

    private LocalDate departureDate;

    private LocalDate arrivalDate;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    private Boolean luggage;

    private Long discount;

    @OneToMany
    private List<Destination> changeovers;

    private Integer duration;

    @OneToMany
    @JsonIgnore
    private List<FlightTicket> tickets;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AirplaneSegment> segments;

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

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public List<AirplaneSegment> getSegments() {
        return segments;
    }

    public void setSegments(List<AirplaneSegment> segments) {
        this.segments = segments;
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

    public Boolean getLuggage() {
        return luggage;
    }

    public void setLuggage(Boolean luggage) {
        this.luggage = luggage;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public List<FlightTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<FlightTicket> tickets) {
        this.tickets = tickets;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}

