package com.sparrow.controller;

import com.sparrow.model.hotel.Hotel;
import com.sparrow.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "api/public/hotels")
public class HotelController {

    private Logger logger = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<Hotel>> getHotels() {
        return ResponseEntity.ok(hotelService.findAll());
    }

    @PostMapping
    public ResponseEntity<Hotel> postHotel(@RequestBody Hotel hotel) {
        logger.info("Creating new hotel \n");
        logger.info(String.format("Name: %s \nDescription: %s", hotel.getName(), hotel.getDescription()));
        logger.info("Saving hotel to database...");
        return ResponseEntity.ok(hotelService.save(hotel));
    }


    @GetMapping(value = "/details/**")
    public String getHotelDetails() {
        logger.info("Getting hotel details...");
        return "/index.html";
    }
}
