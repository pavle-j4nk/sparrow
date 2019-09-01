package com.sparrow.backend.service.impl;

import com.sparrow.backend.model.Address;
import com.sparrow.backend.repository.AddressRepository;
import com.sparrow.backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }
}
