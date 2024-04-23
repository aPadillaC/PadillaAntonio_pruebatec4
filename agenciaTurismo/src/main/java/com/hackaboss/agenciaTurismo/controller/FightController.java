package com.hackaboss.agenciaTurismo.controller;


import com.hackaboss.agenciaTurismo.dto.FlightDTO;
import com.hackaboss.agenciaTurismo.model.Flight;
import com.hackaboss.agenciaTurismo.service.IClientService;
import com.hackaboss.agenciaTurismo.service.IFlightBookingService;
import com.hackaboss.agenciaTurismo.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agency/flights")
public class FightController {

    @Autowired
    private IFlightService flightService;

    @Autowired
    private IFlightBookingService flightBookingService;

    @Autowired
    private IClientService clientService;


    // 1. Add flight
    @PostMapping("/new")
    public String addFlight(@RequestBody Flight flight){

        flightService.addFlight(flight);

        return "Flight added";
    }



    // 2. Get flights
    @GetMapping
    public List<FlightDTO> getFlights(){

        return flightService.getFlights();

    }


    // 3. Find flight by id
    @GetMapping("/{flightId}")
    public FlightDTO getFlightById(@PathVariable Integer flightId){

        return flightService.getFlightById(flightId);
    }



    // 4. Update flight
    @PutMapping("/edit/{flightId}")
    public String updateFlight(@PathVariable Integer flightId, @RequestBody Flight flight){

        flightService.updateFlight(flightId, flight);

        return "Flight updated";
    }


    //5. Delete flight
    @DeleteMapping("/delete/{flightId}")
    public String deleteFlight(@PathVariable Integer flightId){

        flightService.deleteFlight(flightId);

        return "Flight deleted";
    }

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
