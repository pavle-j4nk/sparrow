package com.sparrow.backend.service;

import com.sparrow.backend.model.*;
import com.sparrow.backend.repository.HotelServicesRepository;
import com.sparrow.backend.service.exception.HotelServiceAlreadyExistsException;
import com.sparrow.backend.service.impl.ExtraServicesServiceImpl;
import com.sparrow.backend.service.impl.HotelServiceImpl;
import com.sparrow.backend.service.impl.HotelServicesServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HotelServicesServiceIntegrationTest {

    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String HOTEL = "HOTEL";
    public static final String SERVICE = "SERVICE";
    public static final String TRANSPORT = "TRANSPORT";
    public static final String ASDF = "ASDF";
    @Mock
    private HotelServicesRepository hotelServicesRepository;

    @Mock
    private HotelServices hotelServices;

    @InjectMocks
    private HotelServicesServiceImpl hotelServicesService;

    @Mock
    private HotelServiceImpl hotelService;

    @Mock
    private ExtraServicesServiceImpl extraServicesService;

    @Mock
    private User user;

    @Test
    public void testSave() {
        Hotel h = new Hotel();
        h.setId(1L);
        h.setName(HOTEL);
        h.setDescription(DESCRIPTION);
        h.setAdmin(user);


        Set<Room> rooms = new HashSet<>();
        Room room = new Room("room", 1, h, 1, true);
        Room room2 = new Room("room2", 1, h, 1, true);
        rooms.add(room);

        Set<PriceList> priceLists = new HashSet<>();

        PriceList priceList = new PriceList(h);

        PriceListItem priceListItem = new PriceListItem(room, 1.0, priceList);
        Set<PriceListItem> priceListItems = new HashSet<>();
        priceListItems.add(priceListItem);

        priceList.setItems(priceListItems);
        priceLists.add(priceList);
        h.setPriceLists(priceLists);


        Set<Room> hrRooms = new HashSet<>();
        hrRooms.add(room2);

        Set<HotelServices> hotelServices = new HashSet<>();
        HotelServices hs = new HotelServices();

        ExtraService es = new ExtraService();
        es.setName(TRANSPORT);
        es.setHotelServices(hotelServices);
        es.setId(1L);

        hs.setExtraService(es);
        hs.setHotel(h);
        hs.setPrice(1.0);
        hs.setId(1L);

        hotelServices.add(hs);

        h.setHotelServices(hotelServices);

        when(hotelService.findById(1L)).thenReturn(h);
        when(extraServicesService.findByName(TRANSPORT)).thenReturn(es);
        when(hotelService.save(h)).thenReturn(h);

        assertThrows(HotelServiceAlreadyExistsException.class, () -> {
            hotelServicesService.save(1L, hs);
        });
    }

    @Test
    public void testSave_Different_service() {
        Hotel h = new Hotel();
        h.setId(1L);
        h.setName(HOTEL);
        h.setDescription(DESCRIPTION);
        h.setAdmin(user);


        Set<Room> rooms = new HashSet<>();
        Room room = new Room("room", 1, h, 1, true);
        Room room2 = new Room("room2", 1, h, 1, true);
        rooms.add(room);

        Set<PriceList> priceLists = new HashSet<>();

        PriceList priceList = new PriceList(h);

        PriceListItem priceListItem = new PriceListItem(room, 1.0, priceList);
        Set<PriceListItem> priceListItems = new HashSet<>();
        priceListItems.add(priceListItem);

        priceList.setItems(priceListItems);
        priceLists.add(priceList);
        h.setPriceLists(priceLists);


        Set<Room> hrRooms = new HashSet<>();
        hrRooms.add(room2);

        Set<HotelServices> hotelServices = new HashSet<>();
        HotelServices hs = new HotelServices();

        ExtraService es = new ExtraService();
        es.setName(TRANSPORT);
        es.setHotelServices(hotelServices);
        es.setId(1L);

        hs.setExtraService(es);
        hs.setHotel(h);
        hs.setPrice(1.0);
        hs.setId(1L);

        HotelServices hs2 = new HotelServices();

        ExtraService es2 = new ExtraService();
        es2.setName(ASDF);
        es2.setHotelServices(hotelServices);
        es2.setId(1L);

        hs2.setExtraService(es2);
        hs2.setHotel(h);
        hs2.setPrice(1.0);
        hs2.setId(1L);

        hotelServices.add(hs);

        h.setHotelServices(hotelServices);

        when(hotelService.findById(1L)).thenReturn(h);
        when(extraServicesService.findByName(TRANSPORT)).thenReturn(es);
        when(hotelService.save(h)).thenReturn(h);

        Hotel result = hotelService.save(h);
        assertEquals(result, h);
    }

    @Test
    public void testDelete() {
        when(hotelServicesService.delete(1L, 1L)).thenReturn(null);
        Hotel h = hotelServicesService.delete(1L, 1L);
        assertNull(h);
    }
}
