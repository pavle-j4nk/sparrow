package com.sparrow.backend.service;


import com.sparrow.backend.model.*;
import com.sparrow.backend.repository.PriceListItemRepository;
import com.sparrow.backend.service.impl.HotelServiceImpl;
import com.sparrow.backend.service.impl.PriceListItemServiceImpl;
import com.sparrow.backend.service.impl.RoomServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PriceListItemServiceIntegrationTest {

    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String HOTEL = "HOTEL";
    @Mock
    private PriceListItemRepository priceListItemRepository;

    @Mock
    private PriceListItem priceListItem;

    @Mock
    private HotelServiceImpl hotelService;

    @Mock
    private RoomServiceImpl roomService;

    @Mock
    private PriceListService priceListService;

    @Mock
    private Set<PriceListItem> priceListItems;

    @Mock
    private PriceList pl;

    @Mock
    private User user;

    @InjectMocks
    private PriceListItemServiceImpl priceListItemService;

    @Test
    public void testFindByRoom() {
        Hotel h = new Hotel();
        h.setId(1L);
        h.setName(HOTEL);
        h.setDescription(DESCRIPTION);
        h.setAdmin(user);

        Room room = new Room("room", 1, h, 1, true);

        when(priceListItemRepository.findByRoom(room)).thenReturn(priceListItem);
        PriceListItem result = priceListItemService.findByRoom(room);
        assertNotNull(result);
    }

    @Test
    public void testFindByRoomName() {
        Hotel h = new Hotel();
        h.setId(1L);
        h.setName(HOTEL);
        h.setDescription(DESCRIPTION);
        h.setAdmin(user);

        Room room = new Room("room", 1, h, 1, true);

        when(priceListItemRepository.findByRoomName(room.getName())).thenReturn(priceListItem);
        PriceListItem result = priceListItemService.findByRoomName(room.getName());
        assertNotNull(result);
    }

    @Test
    public void testFindByPricelist() {
        Hotel h = new Hotel();
        h.setId(1L);
        h.setName(HOTEL);
        h.setDescription(DESCRIPTION);
        h.setAdmin(user);

        PriceList priceList = new PriceList(h);
        priceList.setId(1L);

        when(priceListItemRepository.findAllByPriceListId(1L)).thenReturn(new HashSet<>(Arrays.asList(priceListItem)));
        Set<PriceListItem> result = priceListItemService.findByPriceList(priceList);
        assertThat(result).hasSize(1);
    }

    @Test
    public void testCreate() {
        Hotel h = new Hotel();
        h.setId(1L);
        h.setName(HOTEL);
        h.setDescription(DESCRIPTION);
        h.setAdmin(user);

        Set<Room> rooms = new HashSet<>();
        Room room = new Room("room", 1, h, 1, true);
        rooms.add(room);

        Set<PriceList> priceLists = new HashSet<>();

        PriceList priceList = new PriceList(h);

        PriceListItem priceListItem = new PriceListItem(room, 1.0, priceList);
        Set<PriceListItem> priceListItems = new HashSet<>();
        priceListItems.add(priceListItem);

        priceList.setItems(priceListItems);
        priceLists.add(priceList);
        h.setPriceLists(priceLists);

        when(hotelService.findById(1L)).thenReturn(h);
        when(roomService.save(room)).thenReturn(room);
        when(priceListItemRepository.save(priceListItem)).thenReturn(priceListItem);
        when(priceListService.save(pl)).thenReturn(pl);

        PriceListItem result = priceListItemService.create(priceListItem, h.getId());

        assertNotNull(result);
    }

    @Test
    public void testCreate_NoPriceList() {
        Hotel h = new Hotel();
        h.setId(1L);
        h.setName(HOTEL);
        h.setDescription(DESCRIPTION);
        h.setAdmin(user);

        Set<Room> rooms = new HashSet<>();
        Room room = new Room("room", 1, h, 1, true);
        rooms.add(room);

        PriceListItem priceListItem = new PriceListItem(room, 1.0, null);

        h.setPriceLists(new HashSet<>());

        when(hotelService.findById(1L)).thenReturn(h);
        when(roomService.save(room)).thenReturn(room);
        when(priceListItemRepository.save(priceListItem)).thenReturn(priceListItem);
        when(priceListService.save(pl)).thenReturn(pl);

        PriceListItem result = priceListItemService.create(priceListItem, h.getId());

        assertNotNull(result);
    }
}
