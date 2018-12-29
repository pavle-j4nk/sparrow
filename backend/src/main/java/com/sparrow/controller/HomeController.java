package com.sparrow.controller;

import com.sparrow.model.hotel.Hotel;
import com.sparrow.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    HotelService hotelService;

    @GetMapping
    public String root() {
        return "/index.html";
    }

    @GetMapping("/login/**")
    public String login() {
        return "/index.html";
    }

    @GetMapping("/home")
    public String getHome() {
        return "index.html";
    }

    @GetMapping("/user/**")
    public String getUser() {
        return "/index.html";
    }

    @GetMapping("/sa")
    public String getSystemAdmin() {
        return "/index.html";
    }

    @GetMapping("/hotels/**")
    public String getHotels() {
        return "/index.html";
    }


}
