package com.sparrow.service.impl;

import com.sparrow.model.ExtraService;
import com.sparrow.repository.ExtraServicesRepository;
import com.sparrow.service.ExtraServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtraServicesServiceImpl implements ExtraServicesService {
    @Autowired
    private ExtraServicesRepository extraServicesRepository;

    @Override
    public List<ExtraService> findAll() {
        return extraServicesRepository.findAll();
    }

    @Override
    public ExtraService findById(Long id) {
        return extraServicesRepository.findById(id).get();
    }

    @Override
    public ExtraService findByName(String name) {
        return extraServicesRepository.findByName(name);
    }

    @Override
    public ExtraService save(ExtraService extraService) {
        return extraServicesRepository.save(extraService);
    }
}
