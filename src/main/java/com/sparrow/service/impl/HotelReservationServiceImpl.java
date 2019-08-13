package com.sparrow.service.impl;

import com.sparrow.model.HotelReservation;
import com.sparrow.repository.HotelReservationRepository;
import com.sparrow.service.HotelReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelReservationServiceImpl implements HotelReservationService {
    @Autowired
    private HotelReservationRepository hotelReservationRepository;

    @Override
    public HotelReservation save(HotelReservation hotelReservation) {
        return hotelReservationRepository.save(hotelReservation);
    }
}
