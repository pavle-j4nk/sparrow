package com.sparrow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class PriceListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Room room;

    private double price;

    @ManyToOne
    @JsonIgnore
    private PriceList priceList;

    public PriceListItem() {

    }

    public PriceListItem(Room room, double price, PriceList priceList) {
        this.room = room;
        this.price = price;
        this.priceList = priceList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }
}
