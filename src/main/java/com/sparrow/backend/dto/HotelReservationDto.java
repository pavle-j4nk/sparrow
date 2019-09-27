package com.sparrow.backend.dto;

import com.sparrow.backend.model.Room;

import java.sql.Date;
import java.util.Set;

public class HotelReservationDto {
    private Long id;

    private String hotelName;

    private Long hotelId;

    private Set<Room> rooms;

    private Date start;

    private Date end;

    private Double price;

    public HotelReservationDto() {
    }

    public HotelReservationDto(Long id, String hotelName, Set<Room> rooms, Date start, Date end, Double price) {
        this.id = id;
        this.hotelName = hotelName;
        this.rooms = rooms;
        this.start = start;
        this.end = end;
        this.price = price;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
