package com.sparrow.backend.service;

import com.sparrow.backend.model.CarReservation;
import com.sparrow.backend.model.HotelReservation;
import com.sparrow.backend.repository.CarReservationRepository;
import com.sparrow.backend.service.impl.CarReservationServiceImpl;
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
public class CarReservationServiceTest {
    @Mock
    private CarReservationRepository carReservationRepository;

    @Mock
    private CarReservation carReservation;

    @InjectMocks
    private CarReservationServiceImpl carReservationService;

    @Test
    public void testFindAll() {
        when(carReservationRepository.findAll()).thenReturn(Arrays.asList(carReservation));
        List<CarReservation> carReservations = carReservationService.findAll();
        assertThat(carReservations).hasSize(1);
    }
    @Test
    public void testSave() {
        when(carReservationRepository.save(carReservation)).thenReturn(carReservation);
        CarReservation result = carReservationService.save(carReservation);
        assertEquals(result, carReservation);
    }

    @Test
    public void testGetOne() {
        when(carReservationRepository.getOne(1L)).thenReturn(carReservation);
        CarReservation result = carReservationService.findById(1L);
        assertEquals(result, carReservation);
    }



}
