package com.sparrow.backend.service;

import com.sparrow.backend.model.Car;
import com.sparrow.backend.model.CarReservation;

import java.util.Date;
import java.util.List;


public interface CarReservationService {

    CarReservation findById(Long id);

    CarReservation findByStart(Date date);

    CarReservation save(CarReservation carReservation);

    CarReservation update(CarReservation carReservation);

    CarReservation delete(CarReservation carReservation);

    List<CarReservation> findAll();
}
