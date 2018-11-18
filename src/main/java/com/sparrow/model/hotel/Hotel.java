package com.sparrow.model.hotel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String description;

    private Pricelist pricelist;

    private RoomConfiguration roomConfiguration;

    private boolean hasTransferToAirport;

    private boolean hasParking;

    private boolean hasPool;

    private boolean hasRestaurant;

    private boolean hasRoomService;

    private boolean hasWelness;

    private boolean hasSpaCenter;

    private boolean hasWiFi;

    public Hotel() {

    }

    public Hotel(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public Hotel(String name, String address, String description, Pricelist pricelist, RoomConfiguration roomConfiguration,
                 boolean hasTransferToAirport, boolean hasParking, boolean hasPool, boolean hasRestaurant, boolean hasRoomService,
                 boolean hasWelness, boolean hasSpaCenter, boolean hasWiFi) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.pricelist = pricelist;
        this.roomConfiguration = roomConfiguration;
        this.hasTransferToAirport = hasTransferToAirport;
        this.hasParking = hasParking;
        this.hasPool = hasPool;
        this.hasRestaurant = hasRestaurant;
        this.hasRoomService = hasRoomService;
        this.hasWelness = hasWelness;
        this.hasSpaCenter = hasSpaCenter;
        this.hasWiFi = hasWiFi;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pricelist getPricelist() {
        return pricelist;
    }

    public void setPricelist(Pricelist pricelist) {
        this.pricelist = pricelist;
    }

    public RoomConfiguration getRoomConfiguration() {
        return roomConfiguration;
    }

    public void setRoomConfiguration(RoomConfiguration roomConfiguration) {
        this.roomConfiguration = roomConfiguration;
    }

    public boolean isHasTransferToAirport() {
        return hasTransferToAirport;
    }

    public void setHasTransferToAirport(boolean hasTransferToAirport) {
        this.hasTransferToAirport = hasTransferToAirport;
    }

    public boolean isHasParking() {
        return hasParking;
    }

    public void setHasParking(boolean hasParking) {
        this.hasParking = hasParking;
    }

    public boolean isHasPool() {
        return hasPool;
    }

    public void setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
    }

    public boolean isHasRestaurant() {
        return hasRestaurant;
    }

    public void setHasRestaurant(boolean hasRestaurant) {
        this.hasRestaurant = hasRestaurant;
    }

    public boolean isHasRoomService() {
        return hasRoomService;
    }

    public void setHasRoomService(boolean hasRoomService) {
        this.hasRoomService = hasRoomService;
    }

    public boolean isHasWelness() {
        return hasWelness;
    }

    public void setHasWelness(boolean hasWelness) {
        this.hasWelness = hasWelness;
    }

    public boolean isHasSpaCenter() {
        return hasSpaCenter;
    }

    public void setHasSpaCenter(boolean hasSpaCenter) {
        this.hasSpaCenter = hasSpaCenter;
    }

    public boolean isHasWiFi() {
        return hasWiFi;
    }

    public void setHasWiFi(boolean hasWiFi) {
        this.hasWiFi = hasWiFi;
    }
}
