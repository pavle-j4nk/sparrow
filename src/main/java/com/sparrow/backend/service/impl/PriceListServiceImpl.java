package com.sparrow.backend.service.impl;

import com.sparrow.backend.model.PriceList;
import com.sparrow.backend.repository.PriceListRepository;
import com.sparrow.backend.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PriceListServiceImpl implements PriceListService {
    @Autowired
    private PriceListRepository priceListRepository;

    @Override
    public PriceList findByHotelId(Long id) {
        return priceListRepository.findByHotelId(id);
    }

    @Override
    public PriceList save(PriceList priceList) {
        return priceListRepository.save(priceList);
    }
}
