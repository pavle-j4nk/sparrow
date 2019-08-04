package com.sparrow.repository;

import com.sparrow.model.HotelReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelReservationRepository extends JpaRepository<HotelReservation, Long> {
}
