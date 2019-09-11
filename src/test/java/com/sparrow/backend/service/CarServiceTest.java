package com.sparrow.backend.service;


import com.sparrow.backend.model.Car;
import com.sparrow.backend.model.PriceListItem;
import com.sparrow.backend.repository.CarRepository;
import com.sparrow.backend.service.impl.CarServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CarServiceTest {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private Car car;

    @Test
    public void testFindById() {
        when(carRepository.findById(1L)).thenReturn(java.util.Optional.of(car));
        Car result = carService.findById(1L);
        assertEquals(result, car);
    }



}
