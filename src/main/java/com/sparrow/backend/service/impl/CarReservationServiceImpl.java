package com.sparrow.backend.service.impl;

import com.sparrow.backend.model.CarReservation;
import com.sparrow.backend.repository.CarReservationRepository;
import com.sparrow.backend.service.CarReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CarReservationServiceImpl implements CarReservationService
{
    @Autowired
    CarReservationRepository carReservationRepository;

    /*TODO : IMPLEMENT CAR RESERVATION METHODS*/
    @Override
    public CarReservation findById(Long id) {
        return null;
    }

    @Override
    public CarReservation findByStart(Date date) {
        return null;
    }

    @Override
    public CarReservation save(CarReservation carReservation) {
        return carReservationRepository.save(carReservation);
    }

    @Override
    public CarReservation update(CarReservation carReservation) {
        return null;
    }

    @Override
    public CarReservation delete(CarReservation carReservation) {
        return null;
    }

    @Override
    public List<CarReservation> findAll() {
        return null;
    }
}
