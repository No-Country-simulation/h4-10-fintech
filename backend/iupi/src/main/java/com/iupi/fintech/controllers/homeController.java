package com.iupi.fintech.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")

public class homeController {

    @GetMapping("")
    public String home(){

        return "hola estoy en el home, acabo de hacer login";
    }


}
