package com.sparrow.controller;

import com.sparrow.model.hotel.Hotel;
import com.sparrow.service.HotelService;
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

    @Autowired
    HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<Hotel>> getHotels() {
            return ResponseEntity.ok(hotelService.findAll());
    }

    @PostMapping
    public Hotel postHotel(@RequestBody Hotel hotel) {
        System.err.println("In postHotel...");
        return hotelService.save(hotel);
    }


    @GetMapping(value = "/details/**")
    public String getHotelDetails() {
        System.err.println("Hotel details..");
        return "/index.html";
    }


}
