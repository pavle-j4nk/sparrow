package com.sparrow.backend.service;


import com.sparrow.backend.dto.RentACarDto;
import com.sparrow.backend.model.Address;
import com.sparrow.backend.model.RentACar;
import com.sparrow.backend.model.Role;
import com.sparrow.backend.model.User;
import com.sparrow.backend.repository.RentACarRepository;
import com.sparrow.backend.service.exception.RentacarNotFoundException;
import com.sparrow.backend.service.impl.RentACarServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RentACarServiceTest {
    public static final String RENTACAR = "rentacar";
    public static final String DESCRIPTION = "description";
    public static final String ADDRESS = "ulica brj 1";
    public static final String USER = "user";
    public static final String EMAIL = "mail@mail.com";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String PASSWORD = "pw";
    public static final String CITY = "city";

    @Mock
    private RentACarRepository rentACarRepositoryMock;

    @Mock
    private RentACar rentACarMock;

    @InjectMocks
    private RentACarServiceImpl rentACarService;

    @Test
    public void testFindAll(){
        when(rentACarRepositoryMock.findAll()).thenReturn(Arrays.asList(new RentACar(RENTACAR , DESCRIPTION,
                                                new User(), new Address(), CITY)));

        List<RentACar> rentACars = rentACarService.findAll();
        assertThat(rentACars).hasSize(1);

        verify(rentACarRepositoryMock , times(1)).findAll();
        verifyNoMoreInteractions(rentACarRepositoryMock);
    }

    @Test
    public void testFindAllExtended(){
        RentACar r = new RentACar(RENTACAR , DESCRIPTION,
                new User(), new Address(), CITY);
        r.setAdmin(new User(USER, EMAIL, FIRSTNAME, LASTNAME, ADDRESS, PASSWORD, true, new Role("ROLE_USER")));
        r.setId(1L);

        when(rentACarRepositoryMock.findAll()).thenReturn(Arrays.asList(r));
        List<RentACarDto> hotelDtos = rentACarService.findAllExtended();
        assertThat(hotelDtos).hasSize(1);

        verify(rentACarRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(rentACarRepositoryMock);

    }

    @Test
    public void testGetOne() {
        when(rentACarRepositoryMock.getOne(1L)).thenReturn(rentACarMock);
        RentACar rentACar = rentACarService.getOne(1L);
        assertEquals(rentACarMock, rentACar);

        verify(rentACarRepositoryMock, times(1)).getOne(1L);
        verifyNoMoreInteractions(rentACarRepositoryMock);
    }

   /* @Test
    public void testFindById_NotFound() {
        assertThrows(RentacarNotFoundException.class, () -> {
            rentACarService.findById(1L);
        });
    }*/

    @Test
    public void testFindByName() {
        when(rentACarRepositoryMock.findByName(RENTACAR)).thenReturn(java.util.Optional.of(rentACarMock));
        RentACar rentACar = rentACarService.findByName(RENTACAR);
        assertEquals(rentACarMock, rentACar);

        verify(rentACarRepositoryMock, times(1)).findByName(RENTACAR);
        verifyNoMoreInteractions(rentACarRepositoryMock);
    }

    @Test
    public void testFindByName_NotFound() {
        assertThrows(RentacarNotFoundException.class, () -> {
            rentACarService.findByName(RENTACAR);
        });
    }

    @Test
    public void testFindByAddress_NotFound() {
        assertThrows(RentacarNotFoundException.class, () -> {
            rentACarService.findByAddress(ADDRESS);
        });
    }

    @Test
    public void testFindByAddress() {
        when(rentACarRepositoryMock.findByAddress(ADDRESS)).thenReturn(java.util.Optional.of(rentACarMock));
        RentACar rentACar = rentACarService.findByAddress(ADDRESS);
        assertEquals(rentACarMock, rentACar);

        verify(rentACarRepositoryMock, times(1)).findByAddress(ADDRESS);
        verifyNoMoreInteractions(rentACarRepositoryMock);
    }

    @Test
    public void testFindById() {
        when(rentACarRepositoryMock.findById(1L)).thenReturn(java.util.Optional.of(rentACarMock));
        RentACar rentACar = rentACarService.findById(1L);
        assertEquals(rentACarMock, rentACar);

        verify(rentACarRepositoryMock, times(1)).findById(1L);
        verifyNoMoreInteractions(rentACarRepositoryMock);
    }

    @Test
    public void testSave() {
        when(rentACarRepositoryMock.save(rentACarMock)).thenReturn(rentACarMock);

        RentACar rentACar = rentACarService.save(rentACarMock);

        assertEquals(rentACar, rentACarMock);
        verify(rentACarRepositoryMock, times(1)).save(rentACarMock);
        verifyNoMoreInteractions(rentACarRepositoryMock);
    }

    @Test
    public void testUpdate() {
        when(rentACarRepositoryMock.save(rentACarMock)).thenReturn(rentACarMock);
        when(rentACarRepositoryMock.findById(1L)).thenReturn(Optional.of(rentACarMock));

        RentACar rentACarToUpdate = new RentACar();

        rentACarToUpdate.setId(1L);
        rentACarToUpdate.setName(RENTACAR);
        rentACarToUpdate.setDescription(DESCRIPTION);
        rentACarToUpdate.setAddress(new Address(ADDRESS, 22.2, 22.2));

        RentACar rentACar = rentACarService.update(rentACarToUpdate, rentACarMock.getId());

        assertNotNull(rentACar);
    }

    @Test
    public void testUpdate_NoValuePresent() {
        when(rentACarRepositoryMock.save(rentACarMock)).thenReturn(rentACarMock);

        RentACar rentACarToUpdate = new RentACar();

        assertThrows(NoSuchElementException.class, () -> {
            rentACarService.update(rentACarToUpdate , rentACarMock.getId());
        });
    }
}
