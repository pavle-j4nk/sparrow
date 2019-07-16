package com.sparrow.controller;

import com.sparrow.dto.NewHotelDto;
import com.sparrow.model.hotel.Hotel;
import com.sparrow.service.HotelService;
import com.sparrow.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "api/public/hotels")
public class HotelController {

    private Logger logger = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    HotelService hotelService;

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<Hotel>> getHotels() {
        return ResponseEntity.ok(hotelService.findAll());
    }

    @PostMapping
    public ResponseEntity<Hotel> postHotel(@RequestBody NewHotelDto newHotelDto) {
        Hotel hotel = hotelService.create(newHotelDto);
        logger.info(String.format("Creating new hotel...\nName: %s\nDescription: %s\nAdministrator email: %s", hotel.getName(), hotel.getDescription(), hotel.getAdmin().getEmail()));
        return ResponseEntity.ok(hotelService.save(hotel));
    }

    @GetMapping(value = "/details/**")
    public String getHotelDetails() {
        logger.info("Getting hotel details...");
        return "/index.html";
    }

    @GetMapping(value = "/edit/{id}")
    public String getHotelEdit(@PathVariable Long id) {
        logger.info("Passed id :" + id);
        return "/index.html";
    }

    @GetMapping(value = "/delete/{id}")
    public String getHotelDelete(@PathVariable Long id) {
        Hotel hotel = hotelService.findById(id);
        hotelService.delete(hotel);
        return "/sa";
    }
}
