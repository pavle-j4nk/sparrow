package com.sparrow.controller;

import com.sparrow.model.hotel.Hotel;
import com.sparrow.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping(value = "/")
    public List<Hotel> getAllHotels() {
        return hotelService.findAll();
    }
}
