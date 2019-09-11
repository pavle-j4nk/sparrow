package com.sparrow.backend.service;

import com.sparrow.backend.model.ExtraService;
import com.sparrow.backend.repository.ExtraServicesRepository;
import com.sparrow.backend.service.impl.ExtraServicesServiceImpl;
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
public class ExtraServicesServiceTest {

    public static final String DUMMY = "DUMMY";
    @Mock
    private ExtraService extraService;

    @Mock
    private ExtraServicesRepository extraServicesRepository;

    @InjectMocks
    private ExtraServicesServiceImpl extraServicesService;

    @Test
    public void testFindAll() {
        when(extraServicesRepository.findAll()).thenReturn(Arrays.asList(extraService));
        List<ExtraService> result = extraServicesService.findAll();
        assertThat(result).hasSize(1);
    }

    @Test
    public void testFindById() {
        when(extraServicesRepository.findById(1L)).thenReturn(java.util.Optional.of(extraService));
        ExtraService result = extraServicesService.findById(1L);
        assertEquals(result, extraService);
    }

    @Test
    public void testFindByName() {
        when(extraServicesRepository.findByName(DUMMY)).thenReturn(extraService);
        ExtraService result = extraServicesService.findByName(DUMMY);
        assertEquals(result, extraService);
    }

    @Test
    public void testSave() {
        when(extraServicesRepository.save(extraService)).thenReturn(extraService);
        ExtraService saved = extraServicesService.save(extraService);
        assertEquals(saved, extraService);
    }
}
