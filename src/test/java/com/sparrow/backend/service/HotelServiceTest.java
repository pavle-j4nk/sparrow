package com.sparrow.backend.service;

import com.sparrow.backend.dto.HotelDto;
import com.sparrow.backend.dto.NewHotelDto;
import com.sparrow.backend.model.Address;
import com.sparrow.backend.model.Hotel;
import com.sparrow.backend.model.Role;
import com.sparrow.backend.model.User;
import com.sparrow.backend.repository.HotelRepository;
import com.sparrow.backend.service.HotelService;
import com.sparrow.backend.service.impl.HotelNotFoundException;
import com.sparrow.backend.service.impl.HotelServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelServiceTest {

    public static final String HOTEL = "hotel";
    public static final String DESCRIPTION = "description";
    public static final String ADDRESS = "ulica brj 1";
    public static final String USER = "user";
    public static final String EMAIL = "mail@mail.com";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String PASSWORD = "pw";
    @Mock
    private HotelRepository hotelRepositoryMock;

    @Mock
    private Hotel hotelMock;

    @Mock
    private NewHotelDto newHotelDtoMock;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Test
    public void testFindAll() {
        when(hotelRepositoryMock.findAll()).thenReturn(Arrays.asList(new Hotel(HOTEL, DESCRIPTION, new User(), new Address())));
        List<Hotel> hotels = hotelService.findAll();
        assertThat(hotels).hasSize(1);
        verify(hotelRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(hotelRepositoryMock);
    }

    @Test
    public void testFindAllExtended() {
        Hotel h = new Hotel();
        h.setId(1L);
        h.setName(HOTEL);
        h.setDescription(DESCRIPTION);
        h.setAddress(new Address(ADDRESS, 22.4, 22.3));
        h.setAdmin(new User(USER, EMAIL, FIRSTNAME, LASTNAME, ADDRESS, PASSWORD, true, new Role("ROLE_USER")));

        when(hotelRepositoryMock.findAll()).thenReturn(Arrays.asList(h));
        List<HotelDto> hotelDtos = hotelService.findAllExtended();
        assertThat(hotelDtos).hasSize(1);

        verify(hotelRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(hotelRepositoryMock);
    }

    @Test
    public void testGetOne() {
        when(hotelRepositoryMock.getOne(1L)).thenReturn(hotelMock);
        Hotel hotel = hotelService.getOne(1L);
        assertEquals(hotelMock, hotel);

        verify(hotelRepositoryMock, times(1)).getOne(1L);
        verifyNoMoreInteractions(hotelRepositoryMock);
    }

    @Test
    public void testFindById_NotFound() {
        assertThrows(HotelNotFoundException.class, () -> {
            hotelService.findById(1L);
        });
    }

    @Test
    public void testFindByName() {
        when(hotelRepositoryMock.findByName(HOTEL)).thenReturn(java.util.Optional.of(hotelMock));
        Hotel hotel = hotelService.findByName(HOTEL);
        assertEquals(hotelMock, hotel);

        verify(hotelRepositoryMock, times(1)).findByName(HOTEL);
        verifyNoMoreInteractions(hotelRepositoryMock);
    }

    @Test
    public void testFindByName_NotFound() {
        assertThrows(HotelNotFoundException.class, () -> {
            hotelService.findByName(HOTEL);
        });
    }

    @Test
    public void testFindByAddress_NotFound() {
        assertThrows(HotelNotFoundException.class, () -> {
            hotelService.findByAddress(ADDRESS);
        });
    }

    @Test
    public void testFindByAddress() {
        when(hotelRepositoryMock.findByAddress(ADDRESS)).thenReturn(java.util.Optional.of(hotelMock));
        Hotel hotel = hotelService.findByAddress(ADDRESS);
        assertEquals(hotelMock, hotel);

        verify(hotelRepositoryMock, times(1)).findByAddress(ADDRESS);
        verifyNoMoreInteractions(hotelRepositoryMock);
    }

    @Test
    public void testFindById() {
        when(hotelRepositoryMock.findById(1L)).thenReturn(java.util.Optional.of(hotelMock));
        Hotel hotel = hotelService.findById(1L);
        assertEquals(hotelMock, hotel);

        verify(hotelRepositoryMock, times(1)).findById(1L);
        verifyNoMoreInteractions(hotelRepositoryMock);
    }

    @Test
    public void testSave() {
        when(hotelRepositoryMock.save(hotelMock)).thenReturn(hotelMock);

        Hotel hotel = hotelService.save(hotelMock);

        assertEquals(hotel, hotelMock);
        verify(hotelRepositoryMock, times(1)).save(hotelMock);
        verifyNoMoreInteractions(hotelRepositoryMock);
    }

    @Test
    public void testUpdate() {
        when(hotelRepositoryMock.save(hotelMock)).thenReturn(hotelMock);
        when(hotelRepositoryMock.findById(1L)).thenReturn(Optional.of(hotelMock));

        Hotel hotelToUpdate = new Hotel();

        hotelToUpdate.setId(1L);
        hotelToUpdate.setName(HOTEL);
        hotelToUpdate.setDescription(DESCRIPTION);
        hotelToUpdate.setAddress(new Address(ADDRESS, 22.2, 22.2));

        Hotel hotel = hotelService.update(hotelToUpdate);

        assertNotNull(hotel);
    }

    @Test
    public void testUpdate_NoValuePresent() {
        when(hotelRepositoryMock.save(hotelMock)).thenReturn(hotelMock);

        Hotel hotelToUpdate = new Hotel();

        assertThrows(NoSuchElementException.class, () -> {
            hotelService.update(hotelToUpdate);
        });
    }



    @Test
    public void testCreate_NullPointer() {
        assertThrows(NullPointerException.class, () -> {
            hotelService.create(newHotelDtoMock);
        });
    }

    @Test
    public void testCreate() {
        NewHotelDto dto = new NewHotelDto();

        dto.setName(HOTEL);
        dto.setDescription(DESCRIPTION);
        dto.setUserEmail(EMAIL);

        Hotel hotel = hotelService.save(hotelMock);

        assertNull(hotel);
    }
}
