package com.hackaboss.agenciaTurismo.controller;


import com.hackaboss.agenciaTurismo.service.IClientService;
import com.hackaboss.agenciaTurismo.service.IFlightBookingService;
import com.hackaboss.agenciaTurismo.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("agency/flights")
public class FightController {

    @Autowired
    private IFlightService flightService;

    @Autowired
    private IFlightBookingService flightBookingService;

    @Autowired
    private IClientService clientService;


//    @GetMapping
//    public void saveFlight(){
//
//        flightService.createFlightAndAddClients();
//    }

//    @GetMapping
//    public String hello(){
//
//        flightService.deleteFlight();
//        return "Hello World";
//    }
}
