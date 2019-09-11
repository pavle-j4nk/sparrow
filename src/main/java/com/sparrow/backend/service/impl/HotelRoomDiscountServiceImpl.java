package com.sparrow.backend.service.impl;

import com.sparrow.backend.model.HotelReservation;
import com.sparrow.backend.model.HotelRoomDiscount;
import com.sparrow.backend.repository.HotelRoomDiscountRepository;
import com.sparrow.backend.service.HotelReservationService;
import com.sparrow.backend.service.HotelRoomDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class HotelRoomDiscountServiceImpl implements HotelRoomDiscountService {
    @Autowired
    private HotelRoomDiscountRepository hotelRoomDiscountRepository;

    @Autowired
    private HotelReservationService hotelReservationService;

    @Override
    public List<HotelRoomDiscount> findAll() {
        return hotelRoomDiscountRepository.findAll();
    }

    @Override
    public List<HotelRoomDiscount> findAllActive(Date tripStart, Date tripEnd) {
        List<HotelReservation> hotelReservations = hotelReservationService.findActive();
        List<HotelRoomDiscount> hotelRoomDiscounts = hotelRoomDiscountRepository.findAll();
        for (HotelReservation hr : hotelReservations) {
            if (tripStart.before(hr.getStart()) && tripEnd.before(hr.getStart())) {
                continue;
            } else if (hr.getEnd().before(tripStart)) {
                continue;
            } else {
                hotelRoomDiscounts.removeIf(h -> hr.getRooms().contains(h.getPriceListItem().getRoom()));
            }
        }
        return hotelRoomDiscounts;
    }

    @Override
    public HotelRoomDiscount getOne(Long id) {
        return hotelRoomDiscountRepository.getOne(id);
    }

    @Override
    public HotelRoomDiscount save(HotelRoomDiscount hotelRoomDiscount) {
        return hotelRoomDiscountRepository.save(hotelRoomDiscount);
    }
}
