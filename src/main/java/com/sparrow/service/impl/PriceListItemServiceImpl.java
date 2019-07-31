package com.sparrow.service.impl;

import com.sparrow.model.PriceList;
import com.sparrow.model.PriceListItem;
import com.sparrow.repository.PriceListItemRepository;
import com.sparrow.service.PriceListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PriceListItemServiceImpl implements PriceListItemService {
    @Autowired
    private PriceListItemRepository priceListItemRepository;

    @Override
    public Set<PriceListItem> findByPriceList(PriceList priceList) {
        return priceListItemRepository.findAllByPriceListId(priceList.getId());
    }
}
