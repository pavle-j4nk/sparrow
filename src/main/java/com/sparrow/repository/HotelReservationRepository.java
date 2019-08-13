package com.sparrow.repository;

import com.sparrow.model.HotelReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface HotelReservationRepository extends JpaRepository<HotelReservation, Long> {

    @Query("SELECT h FROM HotelReservation h WHERE (h.start >= :start AND h.start <= :end) OR (h.end >=:start AND h.end <= :end)")
    List<HotelReservation> findByDate(@Param("start") Date start, @Param("end") Date end);
}
