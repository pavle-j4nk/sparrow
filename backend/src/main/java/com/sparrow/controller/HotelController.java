package com.sparrow.controller;

import com.sparrow.model.hotel.Hotel;
import com.sparrow.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "hotels")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<Hotel>> getHotels() {
        System.err.println("In Hotel Controller"); // TODO: configure LOG4j
        List<Hotel> hotels =  hotelService.findAll();
        return ResponseEntity.ok(hotelService.findAll());
    }

    @GetMapping(value = "/details/**")
    public String getHotelDetails() {
        System.err.println("Hotel details..");
        return "/index.html";
    }


}
