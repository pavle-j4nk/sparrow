package com.sparrow.service.impl;

import com.sparrow.dto.NewHotelDto;
import com.sparrow.model.Hotel;
import com.sparrow.repository.HotelRepository;
import com.sparrow.service.HotelService;
import com.sparrow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getOne(Long id) {
        return hotelRepository.getOne(id);
    }

    @Override
    public Hotel findOne(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            return hotel.get();
        } else {
            throw new HotelNotFoundException();
        }
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

    @Override
    public Hotel findByAddress(String address) {
        Optional<Hotel> hotel = hotelRepository.findByAddress(address);
        if (hotel.isPresent()) {
            return hotel.get();
        }
        else {
            throw new HotelNotFoundException();
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

    public Hotel update(Hotel h) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(h.getId());
        Hotel hotel = hotelOptional.get();
        hotel.setName(h.getName());
        hotel.setDescription(h.getDescription());
        hotel.setAddress(h.getAddress());
        return hotelRepository.save(hotel);
    }

    @Override
    public void delete(Hotel hotel) {
        hotelRepository.delete(hotel);
    }

    @Override
    public Hotel create(NewHotelDto newHotelDto) {
        Hotel hotel = new Hotel();
        hotel.setName(newHotelDto.getName());
        hotel.setDescription(newHotelDto.getDescription());
        hotel.setAdmin(userService.findByUsername(newHotelDto.getUserEmail()));
        return hotel;
    }

}
