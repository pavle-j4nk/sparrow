package com.sparrow.backend.service;

import com.sparrow.backend.model.HotelRoomDiscount;

import java.util.List;

public interface HotelRoomDiscountService {
    List<HotelRoomDiscount> findAll();

    HotelRoomDiscount getOne(Long id);

    HotelRoomDiscount save(HotelRoomDiscount hotelRoomDiscount);
}
