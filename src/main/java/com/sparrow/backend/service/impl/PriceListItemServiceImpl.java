package com.sparrow.backend.service.impl;

import com.sparrow.backend.model.Hotel;
import com.sparrow.backend.model.PriceList;
import com.sparrow.backend.model.PriceListItem;
import com.sparrow.backend.model.Room;
import com.sparrow.backend.repository.PriceListItemRepository;
import com.sparrow.backend.repository.RoomRepository;
import com.sparrow.backend.service.HotelService;
import com.sparrow.backend.service.PriceListItemService;
import com.sparrow.backend.service.PriceListService;
import com.sparrow.backend.service.RoomService;
import com.sparrow.backend.service.exception.NotFoundException;
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
    public PriceListItem findByRoom(Room room) {
        return priceListItemRepository.findByRoom(room);
    }

    @Override
    public PriceListItem findByRoomName(String name) {
        return priceListItemRepository.findByRoomName(name);
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
