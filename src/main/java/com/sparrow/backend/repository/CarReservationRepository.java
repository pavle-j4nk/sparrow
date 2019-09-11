package com.sparrow.backend.repository;

import com.sparrow.backend.model.CarReservation;
import com.sparrow.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import javax.persistence.LockModeType;
import java.sql.Date;
import java.util.List;

public interface CarReservationRepository extends JpaRepository<CarReservation , Long> {

    @Query("SELECT c from CarReservation c where(c.start >= :start AND c.start <= :end) OR (c.end >=:start AND c.end <= :end)")
    List<CarReservation> findByDate(@Param("start") Date start , @Param("end") Date end) ;

    @Lock(LockModeType.PESSIMISTIC_READ)
    CarReservation save(CarReservation carReservation);

    List<CarReservation> findByUser(User user);

}
