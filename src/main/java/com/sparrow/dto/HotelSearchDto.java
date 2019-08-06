package com.sparrow.dto;

import java.sql.Date;

public class HotelSearchDto {
    /**
     *  Hotel or destination name
     */
    private String place;

    private Date start;

    private Date end;

    private int rooms;

    private int guests;

    public HotelSearchDto(String place, Date start, Date end, int rooms, int guests) {
        this.place = place;
        this.start = start;
        this.end = end;
        this.rooms = rooms;
        this.guests = guests;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }
}
