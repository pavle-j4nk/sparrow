package com.sparrow.service.impl;

import com.sparrow.model.Address;
import com.sparrow.repository.AddressRepository;
import com.sparrow.service.AddressService;
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
