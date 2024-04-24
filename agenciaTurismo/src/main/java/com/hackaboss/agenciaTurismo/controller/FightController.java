package com.hackaboss.agenciaTurismo.controller;


import com.hackaboss.agenciaTurismo.dto.FlightBookingDTO;
import com.hackaboss.agenciaTurismo.dto.FlightDTO;
import com.hackaboss.agenciaTurismo.model.Flight;
import com.hackaboss.agenciaTurismo.service.IClientService;
import com.hackaboss.agenciaTurismo.service.IFlightBookingService;
import com.hackaboss.agenciaTurismo.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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



    //6. Get flight by destination, origin and date
    @GetMapping("/search")
    public List<FlightDTO> getFlightByDestinationOriginAndDate(@RequestParam("destination") String destination,
                                                               @RequestParam("origin") String origin,
                                                               @RequestParam("date") LocalDate date){

        return flightService.getFlightByDestinationOriginAndDate(destination, origin, date);
    }



    //7. Add flight-booking
    @PostMapping("/{flightId}/flight-booking/new")
    public String addFlightBooking(@PathVariable Integer flightId, @RequestBody FlightBookingDTO flightBookingDTO){

        Double price = flightService.addFlightBooking(flightId, flightBookingDTO);

        return "Flight-booking added, price: " + price + " €";
    }



    //8. Get flight-booking by id
    @GetMapping("/{flightId}/flight-booking")
    public List<FlightBookingDTO> getFlightBookingForFlightById(@PathVariable Integer flightId){

        return flightService.getFlightBookingForFlightById(flightId);
    }



    //9. Update flight-booking
    @PutMapping("/flight-booking/edit/{flightBookingId}")
    public String updateFlightBooking(@PathVariable Integer flightBookingId, @RequestBody FlightBookingDTO flightBookingDTO){

        flightService.updateFlightBooking(flightBookingId, flightBookingDTO);

        return "Flight-booking updated";
    }



    //9. Delete flight-booking
    @DeleteMapping("/flight-booking/delete/{flightBookingId}")
    public String deleteFlightBooking(@PathVariable Integer flightBookingId){

        flightService.deleteFlightBooking(flightBookingId);

        return "Flight-booking deleted";
    }

    //10. Add flightList
    @PostMapping("/newList")
    public String addFlightList(@RequestBody List<Flight> flightList){

        flightService.addFlightList(flightList);

        return "Flight list added";
    }


}
