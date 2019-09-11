package com.sparrow.backend.service;

import com.sparrow.backend.dto.HotelSearchDto;
import com.sparrow.backend.dto.NewHotelDto;
import com.sparrow.backend.dto.RoomSearchDto;
import com.sparrow.backend.model.*;
import com.sparrow.backend.repository.HotelRepository;
import com.sparrow.backend.repository.HotelReservationRepository;
import com.sparrow.backend.repository.HotelRoomDiscountRepository;
import com.sparrow.backend.service.impl.HotelServiceImpl;
import com.sparrow.backend.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HotelServiceIntegrationTest {
    public static final String HOTEL = "hotel";
    public static final String DESCRIPTION = "description";
    public static final String ADDRESS = "ulica brj 1";
    public static final String USER = "user";
    public static final String EMAIL = "mail@mail.com";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String PASSWORD = "pw";

    @Mock
    private HotelReservationRepository hotelReservationRepository;

    @Mock
    private HotelRoomDiscountRepository hotelRoomDiscountRepository;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private User user;

    @Mock
    private Hotel hotelMock;

    @Mock
    private NewHotelDto newHotelDtoMock;

    @Mock
    private HotelSearchDto hotelSearchDto;

    @Mock
    private HotelRoomDiscount hotelRoomDiscount;

    @Mock
    private RoomSearchDto roomSearchDto;

    @Mock
    private HotelRepository hotelRepositoryMock;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Test
    public void testCreate() {
        user = new User(USER, EMAIL, FIRSTNAME, LASTNAME, ADDRESS, PASSWORD, true, new Role("ROLE_HOTEL_ADMIN"));

        when(userService.findById(1L)).thenReturn(user);
        when(userService.findByUsername(EMAIL)).thenReturn(user);

        User user = userService.findById(1L);

        NewHotelDto dto = new NewHotelDto();

        dto.setName(HOTEL);
        dto.setDescription(DESCRIPTION);
        dto.setUserEmail(user.getEmail());

        Hotel hotel = hotelService.create(dto);

        assertEquals(hotel.getName(), dto.getName());
    }

    @Test
    public void testUpdateRoom() {
        user = new User(USER, EMAIL, FIRSTNAME, LASTNAME, ADDRESS, PASSWORD, true, new Role("ROLE_HOTEL_ADMIN"));
        Hotel h = new Hotel();
        Room room = new Room("room", 1, h, 1, true);
        Room roomToUpdate = new Room("room2", 2, h, 2, false);
        room.setId(1L);
        roomToUpdate.setId(1L);

        Set<Room> rooms = new HashSet<>();
        rooms.add(room);
        hotelRepositoryMock.findById(1L);
        h.setName(HOTEL);
        h.setDescription(DESCRIPTION);
        h.setAdmin(user);
        h.setRooms(rooms);

        when(hotelRepositoryMock.findById(1L)).thenReturn(Optional.of(h));
        when(hotelRepositoryMock.save(hotelMock)).thenReturn(hotelMock);
        when(hotelService.updateRoom(1L, 1L, roomToUpdate)).thenReturn(h);

        Hotel hotel = hotelService.updateRoom(1L, 1L, roomToUpdate);
        assertEquals(hotel.getRooms().iterator().next().getName(), roomToUpdate.getName());
    }

    @Test
    public void testSearch() {
        hotelSearchDto = new HotelSearchDto("za", new Date(1567980000000L), new Date(1569708000000L));
        User admin = new User(USER, EMAIL, FIRSTNAME, LASTNAME, ADDRESS, PASSWORD, true, new Role("ROLE_HOTEL_ADMIN"));

        Hotel h = new Hotel();
        h.setId(1L);
        h.setName(HOTEL);
        h.setDescription(DESCRIPTION);
        h.setAdmin(admin);

        Set<Room> rooms = new HashSet<>();
        Room room = new Room("room", 1, h, 1, true);
        rooms.add(room);

        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.setId(1L);
        hotelReservation.setUser(admin);
        hotelReservation.setStart(new Date(1568066400000L));
        hotelReservation.setEnd(new Date(1568930400000L));
        hotelReservation.setPrice(20.0);
        hotelReservation.setRooms(rooms);

        when(hotelRepositoryMock.findAllByName("za")).thenReturn(Arrays.asList(new Hotel("Plaza", "Description", admin, new Address(ADDRESS, 22.2, 22.2))));
        when(hotelReservationRepository.findAll()).thenReturn(Arrays.asList(hotelReservation));

        List<Hotel> retVals = hotelService.search(hotelSearchDto);
        assertNotNull(retVals);
    }

    @Test
    public void testSearchRooms() {
        roomSearchDto = new RoomSearchDto(new Date(1567980000000L), new Date(1569708000000L), 1, 1, 1);
        user = new User(USER, EMAIL, FIRSTNAME, LASTNAME, ADDRESS, PASSWORD, true, new Role("ROLE_HOTEL_ADMIN"));

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

        when(hotelRepositoryMock.findById(1L)).thenReturn(Optional.of(h));

        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.setId(1L);
        hotelReservation.setUser(user);
        hotelReservation.setStart(new Date(1568066400000L));
        hotelReservation.setEnd(new Date(1568930400000L));
        hotelReservation.setPrice(20.0);
        hotelReservation.setRooms(rooms);

        when(hotelReservationRepository.findByDate(roomSearchDto.getStart(), roomSearchDto.getEnd())).thenReturn(Arrays.asList(hotelReservation));

        Set<PriceListItem> plitems = hotelService.searchRooms(roomSearchDto, h.getId());
        assertEquals(plitems.size(), 0);
    }

    @Test
    public void testSearchRooms_Exact_Beds_No() {
        roomSearchDto = new RoomSearchDto(new Date(1567980000000L), new Date(1569708000000L), 1, 1, 1);
        user = new User(USER, EMAIL, FIRSTNAME, LASTNAME, ADDRESS, PASSWORD, true, new Role("ROLE_HOTEL_ADMIN"));

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

        when(hotelRepositoryMock.findById(1L)).thenReturn(Optional.of(h));
        Set<Room> hrRooms = new HashSet<>();
        hrRooms.add(room2);

        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.setId(1L);
        hotelReservation.setUser(user);
        hotelReservation.setStart(new Date(1568066400000L));
        hotelReservation.setEnd(new Date(1568930400000L));
        hotelReservation.setPrice(20.0);
        hotelReservation.setRooms(hrRooms);

        when(hotelReservationRepository.findByDate(roomSearchDto.getStart(), roomSearchDto.getEnd())).thenReturn(Arrays.asList(hotelReservation));

        Set<PriceListItem> plitems = hotelService.searchRooms(roomSearchDto, h.getId());
        assertEquals(plitems.size(), 1);
    }

    @Test
    public void testSearchRoomsByCurrentDate() {
        user = new User(USER, EMAIL, FIRSTNAME, LASTNAME, ADDRESS, PASSWORD, true, new Role("ROLE_HOTEL_ADMIN"));

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

        when(hotelRepositoryMock.findById(1L)).thenReturn(Optional.of(h));

        Set<Room> hrRooms = new HashSet<>();
        hrRooms.add(room2);

        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.setId(1L);
        hotelReservation.setUser(user);
        hotelReservation.setStart(new Date(1568066400000L));
        hotelReservation.setEnd(new Date(1568930400000L));
        hotelReservation.setPrice(20.0);
        hotelReservation.setRooms(hrRooms);

        when(hotelReservationRepository.findByCurrentDate(any())).thenReturn(Arrays.asList(hotelReservation));

        Set<PriceListItem> result = hotelService.searchRoomsByCurrentDate(h.getId());
        assertEquals(result.size(), 1);
    }

    @Test
    public void testFindPriceListItemsByDates_Before_discount() {
        user = new User(USER, EMAIL, FIRSTNAME, LASTNAME, ADDRESS, PASSWORD, true, new Role("ROLE_HOTEL_ADMIN"));

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

        when(hotelRepositoryMock.findById(1L)).thenReturn(Optional.of(h));

        Set<Room> hrRooms = new HashSet<>();
        hrRooms.add(room2);


        Set<HotelServices> hotelServices = new HashSet<>();
        HotelServices hs = new HotelServices();

        ExtraService es = new ExtraService();
        es.setName("TRANSPORT");
        es.setHotelServices(hotelServices);
        es.setId(1L);

        hs.setExtraService(es);
        hs.setHotel(h);
        hs.setPrice(1.0);
        hs.setId(1L);

        hotelRoomDiscount = new HotelRoomDiscount();
        hotelRoomDiscount.setId(1L);
        hotelRoomDiscount.setDiscount(1.0);
        hotelRoomDiscount.setHotelServices(hotelServices);
        hotelRoomDiscount.setPriceListItem(priceListItems.iterator().next());
        hotelRoomDiscount.setValidFrom(new Date(1568066400000L));
        hotelRoomDiscount.setValidTo(new Date(1568930400000L));

        when(hotelRoomDiscountRepository.findAll()).thenReturn(Arrays.asList(hotelRoomDiscount));
        Set<PriceListItem> result = hotelService.findPriceListItemsByDates(1L, new Date(1567288800000L), new Date(1567634400000L));
        assertEquals(result.size(), 1);
    }

    @Test
    public void testFindPriceListItemsByDates_Intercepts_discount() {
        user = new User(USER, EMAIL, FIRSTNAME, LASTNAME, ADDRESS, PASSWORD, true, new Role("ROLE_HOTEL_ADMIN"));

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

        when(hotelRepositoryMock.findById(1L)).thenReturn(Optional.of(h));

        Set<Room> hrRooms = new HashSet<>();
        hrRooms.add(room2);


        Set<HotelServices> hotelServices = new HashSet<>();
        HotelServices hs = new HotelServices();

        ExtraService es = new ExtraService();
        es.setName("TRANSPORT");
        es.setHotelServices(hotelServices);
        es.setId(1L);

        hs.setExtraService(es);
        hs.setHotel(h);
        hs.setPrice(1.0);
        hs.setId(1L);

        hotelRoomDiscount = new HotelRoomDiscount();
        hotelRoomDiscount.setId(1L);
        hotelRoomDiscount.setDiscount(1.0);
        hotelRoomDiscount.setHotelServices(hotelServices);
        hotelRoomDiscount.setPriceListItem(priceListItems.iterator().next());
        hotelRoomDiscount.setValidFrom(new Date(1568066400000L));
        hotelRoomDiscount.setValidTo(new Date(1568930400000L));

        when(hotelRoomDiscountRepository.findAll()).thenReturn(Arrays.asList(hotelRoomDiscount));
        Set<PriceListItem> result = hotelService.findPriceListItemsByDates(1L, new Date(1567288800000L), new Date(1568325600000L));
        assertEquals(result.size(), 0);
    }

    @Test
    public void testFindPriceListItemsByDates_After_Discount() {
        user = new User(USER, EMAIL, FIRSTNAME, LASTNAME, ADDRESS, PASSWORD, true, new Role("ROLE_HOTEL_ADMIN"));

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

        when(hotelRepositoryMock.findById(1L)).thenReturn(Optional.of(h));

        Set<Room> hrRooms = new HashSet<>();
        hrRooms.add(room2);


        Set<HotelServices> hotelServices = new HashSet<>();
        HotelServices hs = new HotelServices();

        ExtraService es = new ExtraService();
        es.setName("TRANSPORT");
        es.setHotelServices(hotelServices);
        es.setId(1L);

        hs.setExtraService(es);
        hs.setHotel(h);
        hs.setPrice(1.0);
        hs.setId(1L);

        hotelRoomDiscount = new HotelRoomDiscount();
        hotelRoomDiscount.setId(1L);
        hotelRoomDiscount.setDiscount(1.0);
        hotelRoomDiscount.setHotelServices(hotelServices);
        hotelRoomDiscount.setPriceListItem(priceListItems.iterator().next());
        hotelRoomDiscount.setValidFrom(new Date(1568066400000L));
        hotelRoomDiscount.setValidTo(new Date(1568930400000L));

        when(hotelRoomDiscountRepository.findAll()).thenReturn(Arrays.asList(hotelRoomDiscount));
        Set<PriceListItem> result = hotelService.findPriceListItemsByDates(1L, new Date(1569189600000L), new Date(1569794400000L));
        assertEquals(result.size(), 1);
    }
}
