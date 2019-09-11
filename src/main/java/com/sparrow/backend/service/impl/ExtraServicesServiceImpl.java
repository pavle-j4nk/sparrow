package com.sparrow.backend.service.impl;

import com.sparrow.backend.model.ExtraService;
import com.sparrow.backend.repository.ExtraServicesRepository;
import com.sparrow.backend.service.ExtraServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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
