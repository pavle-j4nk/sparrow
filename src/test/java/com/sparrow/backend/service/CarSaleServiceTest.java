package com.sparrow.backend.service;


import com.sparrow.backend.model.Car;
import com.sparrow.backend.model.CarSale;
import com.sparrow.backend.repository.CarSaleRepository;
import com.sparrow.backend.service.impl.CarSaleServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CarSaleServiceTest {
    @Mock
    private CarSaleRepository carSaleRepository;

    @Mock
    private CarSale carSale;

    @Mock
    private Car car;

    @InjectMocks
    private CarSaleServiceImpl carSaleService;

    @Test
    public void testFindAll() {
        when(carSaleRepository.findAll()).thenReturn(Arrays.asList(carSale));
        List<CarSale> discounts = carSaleService.findAll();
        assertThat(discounts).hasSize(1);
    }



}
