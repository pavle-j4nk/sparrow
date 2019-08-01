package com.sparrow.service;

import com.sparrow.model.PriceList;

public interface PriceListService {
    PriceList findByHotelId(Long id);

    PriceList save(PriceList priceList);
}
