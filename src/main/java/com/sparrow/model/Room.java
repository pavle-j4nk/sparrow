package com.sparrow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Room {

    public static final String HOTEL_ID = "hotel_id";
    public static final String BEDS_NUMBER = "beds_number";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = BEDS_NUMBER)
    private int bedsNo;

    @ManyToOne
    @JoinColumn(name= HOTEL_ID, nullable = false)
    @JsonIgnore
    private Hotel hotel;

    private int floor;

    private boolean balcony;

    public Room() {
    }

    public Room(String name, int bedsNo) {
        this.name = name;
        this.bedsNo = bedsNo;
    }

    public Room(String name, int bedsNo, Hotel hotel, int floor, boolean balcony) {
        this.name = name;
        this.bedsNo = bedsNo;
        this.hotel = hotel;
        this.floor = floor;
        this.balcony = balcony;
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

    public int getBedsNo() {
        return bedsNo;
    }

    public void setBedsNo(int bedsNo) {
        this.bedsNo = bedsNo;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
