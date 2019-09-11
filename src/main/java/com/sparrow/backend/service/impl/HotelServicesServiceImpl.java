package com.sparrow.backend.service.impl;

import com.sparrow.backend.model.ExtraService;
import com.sparrow.backend.model.Hotel;
import com.sparrow.backend.model.HotelServices;
import com.sparrow.backend.repository.HotelServicesRepository;
import com.sparrow.backend.service.ExtraServicesService;
import com.sparrow.backend.service.HotelService;
import com.sparrow.backend.service.HotelServicesService;
import com.sparrow.backend.service.exception.HotelServiceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class HotelServicesServiceImpl implements HotelServicesService {
    @Autowired
    private HotelServicesRepository hotelServicesRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelServicesService hotelServicesService;

    @Autowired
    private ExtraServicesService extraServicesService;

    @Override
    public HotelServices save(Long id, HotelServices hotSer) {
        Hotel hotel = hotelService.findById(id);
        Set<HotelServices> hotelServices = hotel.getHotelServices();
        HotelServices hs = new HotelServices();

        hs.setHotel(hotel);
        ExtraService es = extraServicesService.findByName(hotSer.getExtraService().getName());
        hs.setExtraService(es);
        hs.setPrice(hotSer.getPrice());

        for (HotelServices h : hotelServices) {
            if (h.getExtraService().getName().equals(hotSer.getExtraService().getName())) {
                throw new HotelServiceAlreadyExistsException("Service already exists");
            }
        }
        hotelServices.add(hs);
        hotel.setHotelServices(hotelServices);

        hotelService.save(hotel);
        return hs;
    }

    @Override
    public Hotel delete(Long hotelId, Long serviceId) {
        hotelServicesRepository.delete(hotelServicesRepository.findByExtraServiceId(serviceId));
        return hotelService.findById(hotelId);
    }
}
