package com.sparrow.backend.dto;

public class NewPriceListItemDto {
    private String roomName;

    private int bedsNo;

    private int floor;

    private boolean balcony;

    private double price;

    public NewPriceListItemDto(String roomName, int bedsNo, int floor, boolean balcony, double price) {
        this.roomName = roomName;
        this.bedsNo = bedsNo;
        this.floor = floor;
        this.balcony = balcony;
        this.price = price;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
