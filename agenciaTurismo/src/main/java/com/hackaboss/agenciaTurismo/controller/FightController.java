package com.hackaboss.agenciaTurismo.controller;

import com.hackaboss.agenciaTurismo.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class FightController {

    @Autowired
    private FlightService flightService;


//    @GetMapping
//    public void saveFlight(){
//
//        flightService.createFlightAndAddClients();
//    }

    @GetMapping
    public String hello(){

        flightService.deleteFlight();
        return "Hello World";
    }
}
