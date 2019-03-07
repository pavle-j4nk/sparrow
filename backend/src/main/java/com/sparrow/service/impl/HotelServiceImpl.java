package com.sparrow.service.impl;

import com.sparrow.model.hotel.Hotel;
import com.sparrow.repository.hotel.HotelRepository;
import com.sparrow.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Hotel> hotel = hotelRepository.findByName(name);
        if (hotel.isPresent()) {
            return hotel.get();
        } else {
            throw new HotelNotFoundException(name);
        }
    }

    public Hotel findById(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            return hotel.get();
        }
        else {
            throw new HotelNotFoundException(id);
        }
    }

    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public void delete(Hotel hotel) {
        hotelRepository.delete(hotel);
    }

}
