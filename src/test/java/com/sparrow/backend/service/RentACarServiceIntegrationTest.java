package com.sparrow.backend.service;

import com.sparrow.backend.dto.CarSearchDto;
import com.sparrow.backend.dto.RentACarDto;
import com.sparrow.backend.dto.RentACarSearchDto;
import com.sparrow.backend.model.*;
import com.sparrow.backend.repository.CarRepository;
import com.sparrow.backend.repository.CarReservationRepository;
import com.sparrow.backend.repository.CarSaleRepository;
import com.sparrow.backend.repository.RentACarRepository;
import com.sparrow.backend.service.impl.RentACarServiceImpl;
import com.sparrow.backend.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RentACarServiceIntegrationTest {

    public static final String RENTACAR = "rentacar";
    public static final String DESCRIPTION = "description";
    public static final String ADDRESS = "ulica brj 1";
    public static final String USER = "user";
    public static final String EMAIL = "mail@mail.com";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String PASSWORD = "pw";

    @Mock
    private RentACarRepository rentACarRepositoryMock;

    @Mock
    private CarReservationRepository carReservationRepository;

    @Mock
    private CarSaleRepository carSaleRepository;

    @Mock
    private User user;

    @Mock
    private RentACar rentACarMock;

    @Mock
    private RentACarSearchDto rentACarSearchDto;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private RentACarServiceImpl rentACarService;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private CarSearchDto carSearchDto;

    @Test
    public void testSearchCars() {
        carSearchDto = new CarSearchDto(new Date(1567980000000L), new Date(1569708000000L), 5, 100L);
        user = new User(USER, EMAIL, FIRSTNAME, LASTNAME, ADDRESS, PASSWORD, true, new Role("ROLE_HOTEL_ADMIN"));

        RentACar r = new RentACar();
        r.setId(1L);
        r.setName(RENTACAR);
        r.setDescription(DESCRIPTION);
        r.setAdmin(user);

        Set<Car> cars = new HashSet<>();
        Car car = new Car("Petrol", "BMW", "305", 2013, 60, r, 5);
        cars.add(car);



        when(rentACarRepositoryMock.findById(1L)).thenReturn(Optional.of(r));

        CarReservation carReservation = new CarReservation();
        carReservation.setId(1L);
        carReservation.setUser(user);
        carReservation.setStart(new Date(1568066400000L));
        carReservation.setEnd(new Date(1568930400000L));
        carReservation.setPrice(20.0);
        carReservation.setCars(cars);

        when(carReservationRepository.findByDate(carSearchDto.getStart(), carSearchDto.getEnd())).thenReturn(Arrays.asList(carReservation));
    }


}
