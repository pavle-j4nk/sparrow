package com.sparrow.backend.service;

import com.sparrow.backend.model.ExtraService;

import java.util.List;

public interface ExtraServicesService {
    List<ExtraService> findAll();

    ExtraService findById(Long id);

    ExtraService findByName(String name);

    ExtraService save(ExtraService extraService);
}
