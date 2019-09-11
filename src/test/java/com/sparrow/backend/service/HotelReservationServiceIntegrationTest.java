package com.sparrow.backend.service;

import com.sparrow.backend.dto.HotelReservationDto;
import com.sparrow.backend.model.Hotel;
import com.sparrow.backend.model.HotelReservation;
import com.sparrow.backend.model.Room;
import com.sparrow.backend.model.User;
import com.sparrow.backend.repository.HotelReservationRepository;
import com.sparrow.backend.service.impl.HotelReservationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HotelReservationServiceIntegrationTest {

    public static final String HOTEL = "HOTEL";
    public static final String DESCRIPTION = "DESCRIPTION";
    @Mock
    private HotelReservationRepository hotelReservationRepository;

    @Mock
    private HotelReservation hotelReservation;

    @InjectMocks
    private HotelReservationServiceImpl hotelReservationService;

    @Mock
    private User user;

    @Mock
    private HotelReservationDto dto;

    @Test
    public void testFindByUser() {
        when(hotelReservationRepository.findByUser(user)).thenReturn(Arrays.asList(hotelReservation));
        List<HotelReservation> hr = hotelReservationService.findByUser(user);
        assertThat(hr).hasSize(1);
    }

    @Test
    public void testFindActiveByUser() {
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

        when(hotelReservationRepository.findByUser(user)).thenReturn(reservations);
        List<HotelReservation> hr = hotelReservationService.findActiveByUser(user);
        assertThat(hr).hasSize(0);
    }

    @Test
    public void testFindFinishedByUser() {
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
        hotelReservation.setStart(new Date(1600552800000L));
        hotelReservation.setEnd(new Date(1601071200000L));
        hotelReservation.setPrice(20.0);
        hotelReservation.setRooms(hrRooms);

        List<HotelReservation> reservations = new ArrayList<>();
        reservations.add(hotelReservation);

        when(hotelReservationRepository.findByUser(user)).thenReturn(reservations);
        List<HotelReservation> hr = hotelReservationService.findFinishedByUser(user);
        assertThat(hr).hasSize(0);
    }

    @Test
    public void testFindActive() {
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

        when(hotelReservationRepository.findAll()).thenReturn(reservations);
        List<HotelReservation> hr = hotelReservationService.findActive();
        assertThat(hr).hasSize(0);
    }

    @Test
    public void testCreateReservationDto() {
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

        List<HotelReservationDto> dtos = new ArrayList<>();
        dto.setId(hotelReservation.getId());
        dto.setHotelName(hotelReservation.getRooms().iterator().next().getHotel().getName());
        dto.setStart(hotelReservation.getStart());
        dto.setEnd(hotelReservation.getEnd());
        dto.setRooms(hotelReservation.getRooms());
        dto.setPrice(hotelReservation.getPrice());
        dtos.add(dto);

        dtos = hotelReservationService.createReservationsDto(reservations);

        assertThat(dtos).hasSize(1);
    }

}
