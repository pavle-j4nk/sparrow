package com.sparrow.backend.service;

import com.sparrow.backend.model.HotelReservation;
import com.sparrow.backend.repository.HotelReservationRepository;
import com.sparrow.backend.service.impl.HotelReservationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HotelReservationServiceTest {
    @Mock
    private HotelReservationRepository hotelReservationRepository;

    @Mock
    private HotelReservation hotelReservation;

    @InjectMocks
    private HotelReservationServiceImpl hotelReservationService;

    @Test
    public void testFindAll() {
        when(hotelReservationRepository.findAll()).thenReturn(Arrays.asList(hotelReservation));
        List<HotelReservation> hotelReservations = hotelReservationService.findAll();
        assertThat(hotelReservations).hasSize(1);
    }

    @Test
    public void testSave() {
        when(hotelReservationRepository.save(hotelReservation)).thenReturn(hotelReservation);
        HotelReservation result = hotelReservationService.save(hotelReservation);
        assertEquals(result, hotelReservation);
    }

    @Test
    public void testGetOne() {
        when(hotelReservationRepository.getOne(1L)).thenReturn(hotelReservation);
        HotelReservation result = hotelReservationService.getOne(1L);
        assertEquals(result, hotelReservation);
    }

}
