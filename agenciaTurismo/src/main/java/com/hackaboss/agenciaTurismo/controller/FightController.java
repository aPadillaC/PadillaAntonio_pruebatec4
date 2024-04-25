package com.hackaboss.agenciaTurismo.controller;


import com.hackaboss.agenciaTurismo.dto.FlightBookingDTO;
import com.hackaboss.agenciaTurismo.dto.FlightDTO;
import com.hackaboss.agenciaTurismo.exception.*;
import com.hackaboss.agenciaTurismo.model.Flight;
import com.hackaboss.agenciaTurismo.service.IClientService;
import com.hackaboss.agenciaTurismo.service.IFlightBookingService;
import com.hackaboss.agenciaTurismo.service.IFlightService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.context.MessageSourceResolvable;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<String> addFlight(@Valid @RequestBody Flight flight){

        flightService.addFlight(flight);

        return ResponseEntity.status(HttpStatus.CREATED).body("Flight added");
    }



    // 2. Get flights
    @GetMapping
    public ResponseEntity<List<FlightDTO>> getFlights(){

        return ResponseEntity.status(HttpStatus.OK).body(flightService.getFlights());
    }



    // 3. Find flight by id
    @GetMapping("/{flightId}")
    public ResponseEntity<FlightDTO> getFlightById(@Positive @NotNull @PathVariable Integer flightId){

        return ResponseEntity.status(HttpStatus.FOUND).body(flightService.getFlightById(flightId));
    }



    // 4. Update flight
    @PutMapping("/edit/{flightId}")
    public ResponseEntity<String> updateFlight(@Positive @NotNull @PathVariable Integer flightId, @Valid @RequestBody Flight flight){

        flightService.updateFlight(flightId, flight);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Flight updated");
    }



    //5. Delete flight
    @DeleteMapping("/delete/{flightId}")
    public ResponseEntity<String> deleteFlight(@Positive @NotNull @PathVariable Integer flightId){

        flightService.deleteFlight(flightId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Flight deleted");
    }



    //6. Get flight by destination, origin and date
    @GetMapping("/search")
    public ResponseEntity<List<FlightDTO>> getFlightByDestinationOriginAndDate(@RequestParam("destination") String destination,
                                                               @RequestParam("origin") String origin,
                                                               @DateTimeFormat(
                                                                       iso = DateTimeFormat.ISO.DATE,
                                                                       fallbackPatterns = {"yyy/MM/dd", "dd-MM-yy", "dd/MM/yyy"})
                                                                   @RequestParam("date") LocalDate date){

        return ResponseEntity.status(HttpStatus.OK).body(flightService.getFlightByDestinationOriginAndDate(destination, origin, date));
    }



    //7. Add flight-booking
    @PostMapping("/{flightId}/flight-booking/new")
    public ResponseEntity<String> addFlightBooking(@Positive @NotNull @PathVariable Integer flightId, @Valid @RequestBody FlightBookingDTO flightBookingDTO){

        Double price = flightService.addFlightBooking(flightId, flightBookingDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("Flight-booking added, total price: " + price + " €");
    }



    //8. Get flight-booking by id
    @GetMapping("/{flightId}/flight-booking")
    public ResponseEntity<List<FlightBookingDTO>> getFlightBookingForFlightById(@Positive @NotNull @PathVariable Integer flightId){

        return ResponseEntity.status(HttpStatus.FOUND).body(flightService.getFlightBookingForFlightById(flightId));
    }



    //9. Update flight-booking
    @PutMapping("/flight-booking/edit/{flightBookingId}")
    public ResponseEntity<String> updateFlightBooking(@Positive @NotNull @PathVariable Integer flightBookingId, @Valid @RequestBody FlightBookingDTO flightBookingDTO){

        flightService.updateFlightBooking(flightBookingId, flightBookingDTO);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Flight-booking updated");
    }



    //9. Delete flight-booking
    @DeleteMapping("/flight-booking/delete/{flightBookingId}")
    public ResponseEntity<String> deleteFlightBooking(@Positive @NotNull @PathVariable Integer flightBookingId){

        flightService.deleteFlightBooking(flightBookingId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Flight-booking deleted");
    }



    //10. Add flightList
    @PostMapping("/newList")
    public ResponseEntity<String> addFlightList(@Valid @RequestBody List<Flight> flightList){

        flightService.addFlightList(flightList);

        return ResponseEntity.status(HttpStatus.CREATED).body("Flight list added");
    }



    /**
     * Handle all exceptions
     * @param ex
     * @return
     */



    // Exception handler for various exceptions
    @ExceptionHandler({AllBookedException.class, BookingAlreadyExistsException.class,
             HasBookingsException.class, FlightAlreadyExistsException.class, ParameterConflictException.class})
    public ResponseEntity<Map<String, String>> handleBookingsException(RuntimeException ex) {

        Map<String, String> response = new HashMap<>();

        response.put("error", ex.getMessage());

        // Handle the exception and return an appropriate response to the client.
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }




    @ExceptionHandler({BookingNotFoundException.class, FlightNotFoundException.class})
    public ResponseEntity<Map<String, String>> handleNotFoundException(RuntimeException ex) {

        Map<String, String> response = new HashMap<>();

        response.put("error", ex.getMessage());

        // Handle the exception and return an appropriate response to the client.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }



    // Exception handler for validation exceptions
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, List<String>>> handleHandlerMethodValidationException(HandlerMethodValidationException e) {

        Map<String, List<String>> response = new HashMap<>();
        List<String> errors = e.getAllErrors()
                .stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .toList();

        response.put("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }



    // Exception handler for method argument not valid exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationExceptions(
            MethodArgumentNotValidException e) {
        Map<String, List<String>> response = new HashMap<>();
        List<String> errors = e.getAllErrors()
                .stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .toList();

        response.put("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


}
