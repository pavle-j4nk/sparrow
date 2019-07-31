package com.sparrow.service;

import com.sparrow.model.PriceList;
import com.sparrow.model.PriceListItem;

import java.util.Set;

public interface PriceListItemService {
    Set<PriceListItem> findByPriceList(PriceList priceList);
}
