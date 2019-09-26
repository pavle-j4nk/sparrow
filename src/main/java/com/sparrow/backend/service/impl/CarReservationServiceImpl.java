package com.sparrow.backend.service.impl;

import com.sparrow.backend.dto.CarReservationDto;
import com.sparrow.backend.model.CarReservation;
import com.sparrow.backend.model.User;
import com.sparrow.backend.repository.CarReservationRepository;
import com.sparrow.backend.service.CarReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CarReservationServiceImpl implements CarReservationService
{
    @Autowired
    CarReservationRepository carReservationRepository;

    /*TODO : IMPLEMENT CAR RESERVATION METHODS*/
    @Override
    public CarReservation findById(Long id) {
        return carReservationRepository.getOne(id);
    }

    @Override
    public CarReservation findByStart(Date date) {
        return null;
    }

    @Override
    @Transactional
    public CarReservation save(CarReservation carReservation) {
        return carReservationRepository.save(carReservation);
    }

    @Override
    public CarReservation update(CarReservation carReservation) {
        return null;
    }

    @Override
    public void delete(Long id){
        carReservationRepository.deleteById(id);
    }


    @Override
    public List<CarReservation> findActiveByUser(User user) {
        List<CarReservation> reservations = carReservationRepository.findByUser(user);
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());

        reservations.removeIf(carReservation -> currentDate.after(carReservation.getEnd()));
        return reservations;
    }

    @Override
    public List<CarReservation> findFinishedByUser(User user) {
        List<CarReservation> reservations = carReservationRepository.findByUser(user);
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());

        reservations.removeIf(carReservation -> currentDate.before(carReservation.getEnd()));
        return reservations;
    }

    @Override
    public List<CarReservationDto> createReservationsDto(List<CarReservation> reservations) {
        List<CarReservationDto> carReservationDtos = new ArrayList<>();
        for (CarReservation hr: reservations) {
            CarReservationDto dto = new CarReservationDto();
            dto.setId(hr.getId());
            dto.setRentacarName(hr.getCars().iterator().next().getRentACar().getName());
            dto.setStart(hr.getStart());
            dto.setEnd(hr.getEnd());
            dto.setCars(hr.getCars());
            dto.setPrice(hr.getPrice());
            carReservationDtos.add(dto);
        }
        return carReservationDtos;
    }

    @Override
    public List<CarReservation> findAll() {
        return carReservationRepository.findAll();
    }
}
