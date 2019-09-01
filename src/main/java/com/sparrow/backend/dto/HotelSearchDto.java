package com.sparrow.backend.dto;

import java.sql.Date;

public class HotelSearchDto {
    /**
     * Hotel or destination name
     */
    private String place;

    private Date start;

    private Date end;

    public HotelSearchDto(String place, Date start, Date end) {
        this.place = place;
        this.start = start;
        this.end = end;
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

}
