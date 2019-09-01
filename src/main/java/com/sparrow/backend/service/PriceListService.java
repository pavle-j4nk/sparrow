package com.sparrow.backend.service;

import com.sparrow.backend.model.PriceList;

public interface PriceListService {
    PriceList findByHotelId(Long id);

    PriceList save(PriceList priceList);
}
