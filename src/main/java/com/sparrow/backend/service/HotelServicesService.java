package com.sparrow.backend.service;

import com.sparrow.backend.model.Hotel;
import com.sparrow.backend.model.HotelServices;

public interface HotelServicesService {
    HotelServices save(Long hotelId, HotelServices hotSer);

    Hotel delete(Long hotelId, Long serviceId);
}
