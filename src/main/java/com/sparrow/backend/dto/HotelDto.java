package com.sparrow.backend.dto;

import com.sparrow.backend.model.*;

import java.util.Set;

public class HotelDto {
    private Long id;

    private String name;

    private Address address;

    private String description;

    private Set<PriceList> priceLists;

    private Set<Room> rooms;

    private User admin;

    private Set<HotelServices> hotelServices;

    private String image;

    private double avgScore;

    private double noOfScores;

    public HotelDto() {
    }

    public HotelDto(Long id, String name, Address address, String description, Set<PriceList> priceLists, Set<Room> rooms, User admin, Set<HotelServices> hotelServices, String image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.priceLists = priceLists;
        this.rooms = rooms;
        this.admin = admin;
        this.hotelServices = hotelServices;
        this.image = image;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public double getNoOfScores() {
        return noOfScores;
    }

    public void setNoOfScores(double noOfScores) {
        this.noOfScores = noOfScores;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<PriceList> getPriceLists() {
        return priceLists;
    }

    public void setPriceLists(Set<PriceList> priceLists) {
        this.priceLists = priceLists;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Set<HotelServices> getHotelServices() {
        return hotelServices;
    }

    public void setHotelServices(Set<HotelServices> hotelServices) {
        this.hotelServices = hotelServices;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
