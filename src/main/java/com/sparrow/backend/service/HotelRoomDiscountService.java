package com.sparrow.backend.service;

import com.sparrow.backend.model.HotelRoomDiscount;

import java.sql.Date;
import java.util.List;

public interface HotelRoomDiscountService {
    List<HotelRoomDiscount> findAll();

    List<HotelRoomDiscount> findAllActive(Date tripStart, Date tripEnd);

    HotelRoomDiscount getOne(Long id);

    HotelRoomDiscount save(HotelRoomDiscount hotelRoomDiscount);
}
