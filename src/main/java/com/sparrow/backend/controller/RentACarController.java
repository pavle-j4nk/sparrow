package com.sparrow.backend.controller;

import com.sparrow.backend.dto.RentACarDto;
import com.sparrow.backend.model.Dealership;
import com.sparrow.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "api/rentacars")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RentACarController {

    @Autowired
    private ExceptionHandlerService exceptionHandlerService;

    @Autowired
    private UserService userService;

    @Autowired
    private RentACarService rentACarService;

    @Autowired
    private DealershipService dealershipService;

    @Autowired
    private CarService carService;


}
