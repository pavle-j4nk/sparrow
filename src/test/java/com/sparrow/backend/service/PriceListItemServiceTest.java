package com.sparrow.backend.service;

import com.sparrow.backend.model.PriceListItem;
import com.sparrow.backend.repository.PriceListItemRepository;
import com.sparrow.backend.service.impl.PriceListItemServiceImpl;
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
public class PriceListItemServiceTest {
    @Mock
    private PriceListItemRepository priceListItemRepository;

    @InjectMocks
    private PriceListItemServiceImpl priceListItemService;

    @Mock
    private PriceListItem priceListItem;

    @Test
    public void testFindById() {
        when(priceListItemRepository.findById(1L)).thenReturn(java.util.Optional.of(priceListItem));
        PriceListItem result = priceListItemService.findById(1L);
        assertEquals(result, priceListItem);
    }

    @Test
    public void testSave() {
        when(priceListItemRepository.save(priceListItem)).thenReturn(priceListItem);
        PriceListItem result = priceListItemService.save(priceListItem);
        assertEquals(result, priceListItem);
    }
}
