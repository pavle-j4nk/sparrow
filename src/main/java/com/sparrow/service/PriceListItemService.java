package com.sparrow.service;

import com.sparrow.model.PriceList;
import com.sparrow.model.PriceListItem;
import com.sparrow.model.Room;

import java.util.Set;

public interface PriceListItemService {
    PriceListItem findById(Long id);

    Set<PriceListItem> findByPriceList(PriceList priceList);

    PriceListItem create(PriceListItem priceListItem, Long id);

    PriceListItem save(PriceListItem priceListItem);

    PriceListItem findByRoom(Room room);

    PriceListItem findByRoomName(String name);

    void delete(PriceListItem priceListItem);
}
