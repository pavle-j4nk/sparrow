package com.sparrow.backend.dto;

import org.hibernate.dialect.Ingres9Dialect;

import java.sql.Date;

public class CarSearchDto {

    private Date start;
    private Date end;
    private Integer seats;
    private Long priceLow;

    public CarSearchDto(Date start , Date end , Integer seats , Long priceLow) {
        this.start = start;
        this.end = end;
        this.seats = seats;
        this.priceLow = priceLow;
    }

    public Long getPriceLow() {
        return priceLow;
    }

    public void setPriceLow(Long priceLow) {
        this.priceLow = priceLow;
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

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}
