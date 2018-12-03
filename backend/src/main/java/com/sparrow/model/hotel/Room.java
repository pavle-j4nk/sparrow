package com.sparrow.model.hotel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Room {

    public static final String HOTEL_ID = "hotel_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int bedsNo; //Number of beds in room.

    @ManyToOne
    @JoinColumn(name= HOTEL_ID, nullable = false)
    private Hotel hotel;

    public Room() {
    }

    public Room(String name, int bedsNo) {
        this.name = name;
        this.bedsNo = bedsNo;
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
}
