package com.sparrow.backend.dto;

import java.sql.Date;

public class RoomSearchDto {
    private Date start;

    private Date end;

    private int guests;

    private int rooms;

    private int capacity;

    public RoomSearchDto() {
    }

    public RoomSearchDto(Date start, Date end, int guests, int rooms, int capacity) {
        this.start = start;
        this.end = end;
        this.guests = guests;
        this.rooms = rooms;
        this.capacity = capacity;
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

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
