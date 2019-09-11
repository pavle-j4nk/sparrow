package com.sparrow.backend.controller;

import com.sparrow.backend.model.Destination;
import com.sparrow.backend.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/destination")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @GetMapping
    public List<Destination> getAll() {
        return destinationService.getAll();
    }

}
