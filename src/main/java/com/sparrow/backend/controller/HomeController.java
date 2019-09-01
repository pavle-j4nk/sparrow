package com.sparrow.backend.controller;

import com.sparrow.backend.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    HotelService hotelService;

    @GetMapping
    public String root() {
        return null;
    }

    @GetMapping("/login/**")
    public String login() {
        return null;
    }

    @GetMapping("/home")
    public String getHome() {
        return null;
    }

    @GetMapping("/user/**")
    public String getUser() {
        return null;
    }

    @GetMapping("/sa")
    public String getSystemAdmin() {
        return null;
    }

    @GetMapping("/hotels/**")
    public String getHotels() {
        return null;
    }

}
