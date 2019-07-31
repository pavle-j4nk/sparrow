package com.sparrow.service.impl;

import com.sparrow.model.PriceList;
import com.sparrow.repository.PriceListRepository;
import com.sparrow.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceListServiceImpl implements PriceListService {
    @Autowired
    private PriceListRepository priceListRepository;

    @Override
    public PriceList findByHotelId(Long id) {
        return priceListRepository.findByHotelId(id);
    }
}
