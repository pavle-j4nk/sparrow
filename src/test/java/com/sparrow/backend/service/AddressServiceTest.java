package com.sparrow.backend.service;

import com.sparrow.backend.model.Address;
import com.sparrow.backend.repository.AddressRepository;
import com.sparrow.backend.service.impl.AddressServiceImpl;
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
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private Address address;

    @InjectMocks
    private AddressServiceImpl addressService;

    @Test
    public void testSave() {
        when(addressRepository.save(address)).thenReturn(address);
        Address result = addressService.save(address);
        assertEquals(result, address);
    }
}
