package com.sparrow.dto;

import com.sparrow.model.*;

import java.util.List;

public class HotelDto {
    private Long id;

    private String name;

    private Address address;

    private String description;

    private List<PriceList> priceLists;

    private List<Room> rooms;

    private User admin;

    private List<HotelServices> hotelServices;

    private String image;

    public HotelDto() {
    }

    public HotelDto(Long id, String name, Address address, String description, List<PriceList> priceLists, List<Room> rooms, User admin, List<HotelServices> hotelServices, String image) {
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

    public List<PriceList> getPriceLists() {
        return priceLists;
    }

    public void setPriceLists(List<PriceList> priceLists) {
        this.priceLists = priceLists;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public List<HotelServices> getHotelServices() {
        return hotelServices;
    }

    public void setHotelServices(List<HotelServices> hotelServices) {
        this.hotelServices = hotelServices;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
