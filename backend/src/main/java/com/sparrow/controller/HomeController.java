package com.sparrow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String root() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "index.html";
    }

    @GetMapping("/home")
    public  String getHome() {
        return "index.html";
    }

    /*
        privremeno samo, u svrhe testiranja. ukloniti kasnije.
        TODO: napraviti da se navigation bar prikazuje na svakoj stranici
     */
    @GetMapping("/navbar")
    public String getNavbar() {
        return "index.html";
    }
}
