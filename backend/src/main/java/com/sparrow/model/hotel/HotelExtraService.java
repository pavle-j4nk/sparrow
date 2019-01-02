package com.sparrow.model.hotel;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HotelExtraService {

    @Id
    private String name;

    public HotelExtraService() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
