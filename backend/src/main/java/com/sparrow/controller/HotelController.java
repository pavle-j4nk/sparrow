package com.sparrow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "hotels")
public class HotelController {


    @GetMapping
    public String getHotels() {
        System.err.println("In Hotel Controller"); // TODO: configure LOG4j
        return "index.html";
    }

    @GetMapping(value = "/details/**")
    public String getHotelDetails() {
        System.err.println("Hotel details..");
        return "/index.html";
    }


}
