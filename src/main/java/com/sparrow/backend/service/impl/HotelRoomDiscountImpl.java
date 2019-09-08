package com.sparrow.backend.service.impl;

import com.sparrow.backend.model.HotelRoomDiscount;
import com.sparrow.backend.repository.HotelRoomDiscountRepository;
import com.sparrow.backend.service.HotelRoomDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelRoomDiscountImpl implements HotelRoomDiscountService {
    @Autowired
    private HotelRoomDiscountRepository hotelRoomDiscountRepository;

    @Override
    public List<HotelRoomDiscount> findAll() {
        return hotelRoomDiscountRepository.findAll();
    }

    @Override
    public HotelRoomDiscount getOne(Long id) {
        return hotelRoomDiscountRepository.getOne(id);
    }

    @Override
    public HotelRoomDiscount save(HotelRoomDiscount hotelRoomDiscount) {
        return hotelRoomDiscountRepository.save(hotelRoomDiscount);
    }
}
