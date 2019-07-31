package com.sparrow.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class PriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Hotel hotel;

    @OneToMany
    private Set<PriceListItem> items;

    public PriceList() {

    }

    public PriceList(Hotel hotel) {
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Set<PriceListItem> getItems() {
        return items;
    }

    public void setItems(Set<PriceListItem> items) {
        this.items = items;
    }
}
