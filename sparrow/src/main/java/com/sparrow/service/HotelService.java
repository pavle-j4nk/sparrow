package com.sparrow.service;

import com.sparrow.dto.NewHotelDto;
import com.sparrow.model.hotel.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> findAll();

    Hotel findByName(String name);

    Hotel findById(Long id);

    Hotel save(Hotel hotel);

    void delete(Hotel hotel);

    Hotel create(NewHotelDto newHotelDto);

}
