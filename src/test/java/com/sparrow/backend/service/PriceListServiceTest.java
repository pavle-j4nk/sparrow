package com.sparrow.backend.service;

import com.sparrow.backend.model.PriceList;
import com.sparrow.backend.repository.PriceListRepository;
import com.sparrow.backend.service.impl.PriceListServiceImpl;
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
public class PriceListServiceTest {

    @Mock
    private PriceListRepository priceListRepository;

    @Mock
    private PriceList priceList;

    @InjectMocks
    private PriceListServiceImpl priceListService;

    @Test
    public void testFindByHotelId() {
        when(priceListRepository.findByHotelId(1L)).thenReturn(priceList);
        PriceList result = priceListService.findByHotelId(1L);
        assertEquals(result, priceList);
    }

    @Test
    public void testSave() {
        when(priceListRepository.save(priceList)).thenReturn(priceList);
        PriceList result = priceListService.save(priceList);
        assertEquals(result, priceList);
    }
}
