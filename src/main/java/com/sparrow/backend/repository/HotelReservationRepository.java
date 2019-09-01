package com.sparrow.backend.repository;

import com.sparrow.backend.model.HotelReservation;
import com.sparrow.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface HotelReservationRepository extends JpaRepository<HotelReservation, Long> {

    @Query("SELECT h FROM HotelReservation h WHERE (h.start >= :start AND h.start <= :end) OR (h.end >=:start AND h.end <= :end)")
    List<HotelReservation> findByDate(@Param("start") Date start, @Param("end") Date end);

    @Query("SELECT h FROM HotelReservation h WHERE :currentDate BETWEEN h.start AND h.end")
    List<HotelReservation> findByCurrentDate(@Param("currentDate") Date currentDate);

    List<HotelReservation> findByUser(User user);
}
