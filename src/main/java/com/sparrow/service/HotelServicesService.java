package com.sparrow.service;

import com.sparrow.model.Hotel;
import com.sparrow.model.HotelServices;

public interface HotelServicesService {
    HotelServices save(Long hotelId, HotelServices hotSer);

    Hotel delete(Long hotelId, Long serviceId);
}
