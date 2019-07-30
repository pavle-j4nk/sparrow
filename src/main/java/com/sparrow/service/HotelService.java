package com.sparrow.service;

import com.sparrow.dto.NewHotelDto;
import com.sparrow.model.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> findAll();

    Hotel getOne(Long id);

    Hotel findOne(Long id);

    Hotel findByName(String name);

    Hotel findByAddress(String address);

    Hotel findById(Long id);

    Hotel save(Hotel hotel);

    void delete(Hotel hotel);

    Hotel create(NewHotelDto newHotelDto);

}
