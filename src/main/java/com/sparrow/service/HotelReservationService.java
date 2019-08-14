package com.sparrow.service;

import com.sparrow.dto.HotelReservationDto;
import com.sparrow.model.HotelReservation;
import com.sparrow.model.User;

import java.util.List;

public interface HotelReservationService {
    HotelReservation save(HotelReservation hotelReservation);

    List<HotelReservation> findByUser(User user);

    List<HotelReservationDto> createReservationsDto(List<HotelReservation> reservations);

    List<HotelReservation> findFinishedByUser(User user);

    List<HotelReservation> findActiveByUser(User user);
}
