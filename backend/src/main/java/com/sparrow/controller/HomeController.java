package com.sparrow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String root() {
        return "/index.html";
    }

    @GetMapping("/login/**")
    public String login() {
        return "/index.html";
    }

    @GetMapping("/home")
    public  String getHome() {
        return "index.html";
    }
}
