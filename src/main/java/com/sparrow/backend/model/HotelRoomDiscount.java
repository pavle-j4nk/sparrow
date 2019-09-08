package com.sparrow.backend.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Date;

@Entity
public class HotelRoomDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date validFrom;

    private Date validTo;

    @ManyToOne
    private PriceListItem priceListItem;

    @Min(value = 0)
    @Max(value = 1)
    private Double discount;

    public HotelRoomDiscount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public PriceListItem getPriceListItem() {
        return priceListItem;
    }

    public void setPriceListItem(PriceListItem priceListItem) {
        this.priceListItem = priceListItem;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
