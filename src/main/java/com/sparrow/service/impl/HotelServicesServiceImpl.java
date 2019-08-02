package com.sparrow.service.impl;

import com.sparrow.model.ExtraService;
import com.sparrow.model.Hotel;
import com.sparrow.model.HotelServices;
import com.sparrow.repository.HotelServicesRepository;
import com.sparrow.service.ExtraServicesService;
import com.sparrow.service.HotelService;
import com.sparrow.service.HotelServicesService;
import com.sparrow.service.exception.HotelServiceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
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
