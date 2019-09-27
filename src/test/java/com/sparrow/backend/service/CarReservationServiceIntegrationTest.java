package com.sparrow.backend.service;

import com.sparrow.backend.dto.CarReservationDto;
import com.sparrow.backend.model.Car;
import com.sparrow.backend.model.CarReservation;
import com.sparrow.backend.model.RentACar;
import com.sparrow.backend.model.User;
import com.sparrow.backend.repository.CarReservationRepository;
import com.sparrow.backend.service.impl.CarReservationServiceImpl;
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

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CarReservationServiceIntegrationTest {
    public static final String RENTACAR = "RENTACAR";
    public static final String DESCRIPTION = "DESCRIPTION";

    @Mock
    private CarReservationRepository carReservationRepositoryMock;

    @Mock
    private CarReservation carReservationMock;

    @InjectMocks
    private CarReservationServiceImpl carReservationService;

    @Mock
    private User user;

    @Mock
    private CarReservationDto carReservationDto;

    @Test
    public void testFindActiveByUser() {
        RentACar r = new RentACar();
        r.setId(1L);
        r.setName(RENTACAR);
        r.setDescription(DESCRIPTION);
        r.setAdmin(user);

        Car car = new Car("Petrol", "BMW", "305", 2013, 60, r, 5);

        Set<Car> cars = new HashSet<>();
        cars.add(car);

        carReservationMock = new CarReservation();
        carReservationMock.setId(1L);
        carReservationMock.setUser(user);
        carReservationMock.setStart(new Date(1537394400000L));
        carReservationMock.setEnd(new Date(1538258400000L));
        carReservationMock.setPrice(20.0);
        carReservationMock.setCars(cars);

        List<CarReservation> reservations = new ArrayList<>();
        reservations.add(carReservationMock);

        when(carReservationRepositoryMock.findByUser(user)).thenReturn(reservations);
        List<CarReservation> hr = carReservationService.findActiveByUser(user);
        assertThat(hr).hasSize(0);
    }

    @Test
    public void testFindFinishedByUser() {
        RentACar r = new RentACar();
        r.setId(1L);
        r.setName(RENTACAR);
        r.setDescription(DESCRIPTION);
        r.setAdmin(user);


        Car car = new Car("Petrol", "BMW", "305", 2013, 60, r, 5);

        Set<Car> cars = new HashSet<>();
        cars.add(car);

        carReservationMock = new CarReservation();
        carReservationMock.setId(1L);
        carReservationMock.setUser(user);
        carReservationMock.setStart(new Date(1600552800000L));
        carReservationMock.setEnd(new Date(1601071200000L));
        carReservationMock.setPrice(20.0);
        carReservationMock.setCars(cars);

        List<CarReservation> reservations = new ArrayList<>();
        reservations.add(carReservationMock);

        when(carReservationRepositoryMock.findByUser(user)).thenReturn(reservations);
        List<CarReservation> hr = carReservationService.findFinishedByUser(user);
        assertThat(hr).hasSize(0);
    }

}
