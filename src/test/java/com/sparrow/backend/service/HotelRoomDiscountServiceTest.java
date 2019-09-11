package com.sparrow.backend.service;

import com.sparrow.backend.model.HotelRoomDiscount;
import com.sparrow.backend.repository.HotelRoomDiscountRepository;
import com.sparrow.backend.service.impl.HotelRoomDiscountServiceImpl;
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
public class HotelRoomDiscountServiceTest {

    @Mock
    private HotelRoomDiscountRepository hotelRoomDiscountRepository;

    @Mock
    private HotelRoomDiscount hotelRoomDiscount;

    @InjectMocks
    private HotelRoomDiscountServiceImpl hotelRoomDiscountService;

    @Test
    public void testFindAll() {
        when(hotelRoomDiscountRepository.findAll()).thenReturn(Arrays.asList(hotelRoomDiscount));
        List<HotelRoomDiscount> discounts = hotelRoomDiscountService.findAll();
        assertThat(discounts).hasSize(1);
    }

    @Test
    public void testGetOne() {
        when(hotelRoomDiscountRepository.getOne(1L)).thenReturn(hotelRoomDiscount);
        HotelRoomDiscount result = hotelRoomDiscountService.getOne(1L);
        assertEquals(result, hotelRoomDiscount);
    }

    @Test
    public void testSave() {
        when(hotelRoomDiscountRepository.save(hotelRoomDiscount)).thenReturn(hotelRoomDiscount);
        HotelRoomDiscount result = hotelRoomDiscountService.save(hotelRoomDiscount);
        assertEquals(result, hotelRoomDiscount);
    }
}
