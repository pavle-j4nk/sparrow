package com.sparrow.backend.service.impl;

import com.sparrow.backend.dto.HotelReservationDto;
import com.sparrow.backend.model.HotelReservation;
import com.sparrow.backend.model.User;
import com.sparrow.backend.repository.HotelReservationRepository;
import com.sparrow.backend.service.HotelReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class HotelReservationServiceImpl implements HotelReservationService {
    @Autowired
    private HotelReservationRepository hotelReservationRepository;

    @Override
    public List<HotelReservation> findAll() {
        return hotelReservationRepository.findAll();
    }

    @Override
    @Transactional
    public HotelReservation save(HotelReservation hotelReservation) {
        return hotelReservationRepository.save(hotelReservation);
    }

    @Override
    public HotelReservation getOne(Long id) {
        return hotelReservationRepository.getOne(id);
    }

    @Override
    public List<HotelReservation> findByUser(User user) {
        return hotelReservationRepository.findByUser(user);
    }

    @Override
    public void delete(Long id) {
        HotelReservation hotelReservation = hotelReservationRepository.getOne(id);
        hotelReservationRepository.delete(hotelReservation);
    }

    @Override
    public List<HotelReservation> findActiveByUser(User user) {
        List<HotelReservation> reservations = hotelReservationRepository.findByUser(user);
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());

        reservations.removeIf(hotelReservation -> currentDate.after(hotelReservation.getEnd()));
        return reservations;
    }

    @Override
    public List<HotelReservation> findFinishedByUser(User user) {
        List<HotelReservation> reservations = hotelReservationRepository.findByUser(user);
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());

        reservations.removeIf(hotelReservation -> currentDate.before(hotelReservation.getEnd()));
        return reservations;
    }

    @Override
    public List<HotelReservation> findActive() {
        List<HotelReservation> reservations = hotelReservationRepository.findAll();
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());

        reservations.removeIf(hotelReservation -> currentDate.after(hotelReservation.getEnd()));
        return reservations;
    }

    @Override
    public List<HotelReservationDto> createReservationsDto(List<HotelReservation> reservations) {
        List<HotelReservationDto> hotelReservationDtos = new ArrayList<>();
        for (HotelReservation hr: reservations) {
            HotelReservationDto dto = new HotelReservationDto();
            dto.setId(hr.getId());
            dto.setHotelName(hr.getRooms().iterator().next().getHotel().getName());
            dto.setStart(hr.getStart());
            dto.setEnd(hr.getEnd());
            dto.setRooms(hr.getRooms());
            dto.setPrice(hr.getPrice());
            hotelReservationDtos.add(dto);
        }
        return hotelReservationDtos;
    }
}
