package com.sparrow.service.impl;

import com.sparrow.model.Hotel;
import com.sparrow.model.PriceList;
import com.sparrow.model.PriceListItem;
import com.sparrow.repository.PriceListItemRepository;
import com.sparrow.repository.RoomRepository;
import com.sparrow.service.HotelService;
import com.sparrow.service.PriceListItemService;
import com.sparrow.service.PriceListService;
import com.sparrow.service.RoomService;
import com.sparrow.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PriceListItemServiceImpl implements PriceListItemService {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private PriceListService priceListService;

    @Autowired
    private PriceListItemRepository priceListItemRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public PriceListItem findById(Long id) {
        Optional<PriceListItem> priceListItemOptional = priceListItemRepository.findById(id);
        if (priceListItemOptional.isPresent()) {
            return priceListItemOptional.get();
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public Set<PriceListItem> findByPriceList(PriceList priceList) {
        return priceListItemRepository.findAllByPriceListId(priceList.getId());
    }

    @Override
    public PriceListItem create(PriceListItem item, Long id) {
        Hotel hotel = hotelService.findById(id);
        Set<PriceListItem> items;
        Set<PriceList> priceLists = hotel.getPriceLists();
        item.getRoom().setHotel(hotel);
        roomService.save(item.getRoom());

        if (priceLists.isEmpty()) {
            PriceList p = new PriceList();
            p.setHotel(hotel);
            p.setItems(new HashSet<>());
            priceListService.save(p);

            priceLists.add(p);
            hotel.setPriceLists(priceLists);
        }

        PriceList p = priceLists.stream().findFirst().get();
        item.setPriceList(p);
        items = p.getItems();
        items.add(item);
        PriceListItem priceListItem = priceListItemRepository.save(item);
        priceListService.save(p);

        return priceListItem;
    }

    @Override
    public PriceListItem save(PriceListItem priceListItem) {
        return priceListItemRepository.save(priceListItem);
    }

    @Override
    public void delete(PriceListItem priceListItem) {
        PriceList priceList = priceListItem.getPriceList();
        Set<PriceListItem> priceListItems = priceList.getItems();
        priceListItems.removeIf(p -> p.getRoom().getName().equals(priceListItem.getRoom().getName()));
//        priceList.setItems(priceListItems);
        priceListService.save(priceList);
    }
}
