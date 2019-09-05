package com.sparrow.backend.service;

import com.sparrow.backend.dto.HotelReservationDto;
import com.sparrow.backend.model.HotelReservation;
import com.sparrow.backend.model.User;

import java.util.List;

public interface HotelReservationService {
    List<HotelReservation> findAll();

    HotelReservation save(HotelReservation hotelReservation);

    HotelReservation getOne(Long id);

    List<HotelReservation> findByUser(User user);

    void delete(Long id);

    List<HotelReservationDto> createReservationsDto(List<HotelReservation> reservations);

    List<HotelReservation> findFinishedByUser(User user);

    List<HotelReservation> findActiveByUser(User user);
}
