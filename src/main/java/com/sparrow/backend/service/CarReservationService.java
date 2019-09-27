package com.sparrow.backend.service;

import com.sparrow.backend.dto.CarReservationDto;
import com.sparrow.backend.model.Car;
import com.sparrow.backend.model.CarReservation;
import com.sparrow.backend.model.User;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.Date;
import java.util.List;


public interface CarReservationService {

    CarReservation findById(Long id);

    CarReservation findByStart(Date date);

    CarReservation save(CarReservation carReservation);

    CarReservation update(CarReservation carReservation);

    void delete(Long id);

    List<CarReservation> findActiveByUser(User user);

    List<CarReservation> findFinishedByUser(User user);

    List<CarReservationDto> createReservationsDto(List<CarReservation> reservations);

    List<CarReservation> findAll();
}
