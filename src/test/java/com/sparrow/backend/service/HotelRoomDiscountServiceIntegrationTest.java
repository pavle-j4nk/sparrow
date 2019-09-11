package com.sparrow.backend.service;

import com.sparrow.backend.model.*;
import com.sparrow.backend.repository.HotelRoomDiscountRepository;
import com.sparrow.backend.service.impl.HotelReservationServiceImpl;
import com.sparrow.backend.service.impl.HotelRoomDiscountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HotelRoomDiscountServiceIntegrationTest {

    public static final String HOTEL = "HOTEL";
    public static final String DESCRIPTION = "DESCRIPTION";
    @Mock
    private HotelRoomDiscountRepository hotelRoomDiscountRepository;

    @Mock
    private HotelRoomDiscount hotelRoomDiscount;

    @InjectMocks
    private HotelRoomDiscountServiceImpl hotelRoomDiscountService;

    @Mock
    private HotelReservationServiceImpl hotelReservationService;

    @Mock
    private HotelReservation hotelReservation;

    @Mock
    private User user;

    @Test
    public void testFindAllActive_After_Reservation() {
        Hotel h = new Hotel();
        h.setId(1L);
        h.setName(HOTEL);
        h.setDescription(DESCRIPTION);
        h.setAdmin(user);

        Room room2 = new Room("room2", 1, h, 1, true);

        Set<Room> hrRooms = new HashSet<>();
        hrRooms.add(room2);

        hotelReservation = new HotelReservation();
        hotelReservation.setId(1L);
        hotelReservation.setUser(user);
        hotelReservation.setStart(new Date(1537394400000L));
        hotelReservation.setEnd(new Date(1538258400000L));
        hotelReservation.setPrice(20.0);
        hotelReservation.setRooms(hrRooms);

        List<HotelReservation> reservations = new ArrayList<>();
        reservations.add(hotelReservation);

        List<HotelRoomDiscount> discounts = new ArrayList<>();
        hotelRoomDiscount = new HotelRoomDiscount();
        hotelRoomDiscount.setId(1L);
        hotelRoomDiscount.setValidFrom(new Date(1537394400000L));
        hotelRoomDiscount.setValidTo(new Date(1538258400000L));
        discounts.add(hotelRoomDiscount);

        Set<PriceList> priceLists = new HashSet<>();

        PriceList priceList = new PriceList(h);

        PriceListItem priceListItem = new PriceListItem(room2, 1.0, priceList);
        Set<PriceListItem> priceListItems = new HashSet<>();
        priceListItems.add(priceListItem);

        priceList.setItems(priceListItems);
        priceLists.add(priceList);
        h.setPriceLists(priceLists);

        when(hotelReservationService.findActive()).thenReturn(reservations);
        when(hotelRoomDiscountRepository.findAll()).thenReturn(discounts);
        List<HotelRoomDiscount> result = hotelRoomDiscountService.findAllActive(new Date(1601071200000L), new Date(1601330400000L));

        assertThat(result).hasSize(1);
    }

    @Test
    public void testFindAllActive_Before_Reservation() {
        Hotel h = new Hotel();
        h.setId(1L);
        h.setName(HOTEL);
        h.setDescription(DESCRIPTION);
        h.setAdmin(user);

        Room room2 = new Room("room2", 1, h, 1, true);

        Set<Room> hrRooms = new HashSet<>();
        hrRooms.add(room2);

        hotelReservation = new HotelReservation();
        hotelReservation.setId(1L);
        hotelReservation.setUser(user);
        hotelReservation.setStart(new Date(1537394400000L));
        hotelReservation.setEnd(new Date(1538258400000L));
        hotelReservation.setPrice(20.0);
        hotelReservation.setRooms(hrRooms);

        List<HotelReservation> reservations = new ArrayList<>();
        reservations.add(hotelReservation);

        List<HotelRoomDiscount> discounts = new ArrayList<>();
        hotelRoomDiscount = new HotelRoomDiscount();
        hotelRoomDiscount.setId(1L);
        hotelRoomDiscount.setValidFrom(new Date(1537394400000L));
        hotelRoomDiscount.setValidTo(new Date(1538258400000L));
        discounts.add(hotelRoomDiscount);

        Set<PriceList> priceLists = new HashSet<>();

        PriceList priceList = new PriceList(h);

        PriceListItem priceListItem = new PriceListItem(room2, 1.0, priceList);
        Set<PriceListItem> priceListItems = new HashSet<>();
        priceListItems.add(priceListItem);

        priceList.setItems(priceListItems);
        priceLists.add(priceList);
        h.setPriceLists(priceLists);

        when(hotelReservationService.findActive()).thenReturn(reservations);
        when(hotelRoomDiscountRepository.findAll()).thenReturn(discounts);
        List<HotelRoomDiscount> result = hotelRoomDiscountService.findAllActive(new Date(1443477600000L), new Date(1443564000000L));

        assertThat(result).hasSize(1);
    }

}
