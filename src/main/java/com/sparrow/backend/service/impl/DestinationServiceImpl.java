package com.sparrow.backend.service.impl;

import com.sparrow.backend.model.Destination;
import com.sparrow.backend.repository.DestinationRepository;
import com.sparrow.backend.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DestinationServiceImpl implements DestinationService {
    @Autowired
    private DestinationRepository destinationRepository;

    @Override
    public List<Destination> getAll() {
        return destinationRepository.findAll();
    }

}
