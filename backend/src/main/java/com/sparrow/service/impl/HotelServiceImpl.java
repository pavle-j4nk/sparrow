package com.sparrow.service.impl;

import com.sparrow.model.hotel.Hotel;
import com.sparrow.repository.hotel.HotelRepository;
import com.sparrow.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel findByName(String name) {
        List<Hotel> hotels = hotelRepository.findAll();

        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(name)) {
                return hotel;
            }
        }
        return null;
    }
}
