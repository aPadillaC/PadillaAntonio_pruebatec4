package com.hackaboss.agenciaTurismo.controller;


import com.hackaboss.agenciaTurismo.dto.FlightBookingDTO;
import com.hackaboss.agenciaTurismo.dto.FlightDTO;
import com.hackaboss.agenciaTurismo.model.Flight;
import com.hackaboss.agenciaTurismo.service.IFlightService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;



@RestController
@RequestMapping("agency/flights")
public class FightController {

    @Autowired
    private IFlightService flightService;



    /**
     * 1. Add flight
     **/
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added a flight"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/new")
    public ResponseEntity<String> addFlight(@Valid @RequestBody Flight flight){

        flightService.addFlight(flight);

        return ResponseEntity.status(HttpStatus.CREATED).body("Flight added");
    }



    // 2. Get flights

    /**
     * 2. Get flights
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of flights"),
            @ApiResponse(responseCode = "404", description = "Flights not found")
    })
    @GetMapping
    public ResponseEntity<List<FlightDTO>> getFlights(){

        return ResponseEntity.status(HttpStatus.OK).body(flightService.getFlights());
    }



    /**
     * 3. Find flight by id
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Successfully retrieved flight"),
            @ApiResponse(responseCode = "404", description = "Flight not found")
    })
    @GetMapping("/{flightId}")
    public ResponseEntity<FlightDTO> getFlightById(@Positive @NotNull @PathVariable Integer flightId){

        return ResponseEntity.status(HttpStatus.FOUND).body(flightService.getFlightById(flightId));
    }



    /**
     * 4. Update flight
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Successfully updated flight"),
            @ApiResponse(responseCode = "404", description = "Flight not found")
    })
    @PutMapping("/edit/{flightId}")
    public ResponseEntity<String> updateFlight(@Positive @NotNull @PathVariable Integer flightId, @Valid @RequestBody Flight flight){

        flightService.updateFlight(flightId, flight);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Flight updated");
    }



    /**
     * 5. Delete flight
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Successfully deleted flight"),
            @ApiResponse(responseCode = "404", description = "Flight not found")
    })
    @DeleteMapping("/delete/{flightId}")
    public ResponseEntity<String> deleteFlight(@Positive @NotNull @PathVariable Integer flightId){

        flightService.deleteFlight(flightId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Flight deleted");
    }



    /**
     * 6. Get flight by destination, origin and date
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of flights"),
            @ApiResponse(responseCode = "404", description = "Flights not found")
    })
    @GetMapping("/search")
    public ResponseEntity<List<FlightDTO>> getFlightByDestinationOriginAndDate(@RequestParam("destination") String destination,
                                                               @RequestParam("origin") String origin,
                                                               @DateTimeFormat(
                                                                       iso = DateTimeFormat.ISO.DATE,
                                                                       fallbackPatterns = {"yyyy/MM/dd", "dd-MM-yy", "dd/MM/yyy"})
                                                                   @RequestParam("dateTo") LocalDate dateTo,
                                                                @DateTimeFormat(
                                                                        iso = DateTimeFormat.ISO.DATE,
                                                                        fallbackPatterns = {"yyyy/MM/dd", "dd-MM-yy", "dd/MM/yyy"})
                                                                    @RequestParam("dateFrom") LocalDate dateFrom){

        return ResponseEntity.status(HttpStatus.OK).body(flightService.getFlightByDestinationOriginAndDate(destination, origin, dateTo, dateFrom));
    }



    /**
     * 7. Add flight-booking
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added a flight-booking"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/{flightId}/flight-booking/new")
    public ResponseEntity<String> addFlightBooking(@Positive @NotNull @PathVariable Integer flightId, @Valid @RequestBody FlightBookingDTO flightBookingDTO){

        Double price = flightService.addFlightBooking(flightId, flightBookingDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("Flight-booking added, total price: " + price + " €");
    }



    /**
     * 8. Get flight-booking by id
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Successfully retrieved flight-booking"),
            @ApiResponse(responseCode = "404", description = "Flight-booking not found")
    })
    @GetMapping("/{flightId}/flight-booking")
    public ResponseEntity<List<FlightBookingDTO>> getFlightBookingForFlightById(@Positive @NotNull @PathVariable Integer flightId){

        return ResponseEntity.status(HttpStatus.FOUND).body(flightService.getFlightBookingForFlightById(flightId));
    }



    /**
     * 9. Update flight-booking
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Successfully updated flight-booking"),
            @ApiResponse(responseCode = "404", description = "Flight-booking not found")
    })
    @PutMapping("/flight-booking/edit/{flightBookingId}")
    public ResponseEntity<String> updateFlightBooking(@Positive @NotNull @PathVariable Integer flightBookingId, @Valid @RequestBody FlightBookingDTO flightBookingDTO){

        flightService.updateFlightBooking(flightBookingId, flightBookingDTO);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Flight-booking updated");
    }



    /**
     * 10. Delete flight-booking
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Successfully deleted flight-booking"),
            @ApiResponse(responseCode = "404", description = "Flight-booking not found")
    })
    @DeleteMapping("/flight-booking/delete/{flightBookingId}")
    public ResponseEntity<String> deleteFlightBooking(@Positive @NotNull @PathVariable Integer flightBookingId){

        flightService.deleteFlightBooking(flightBookingId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Flight-booking deleted");
    }



    /**
     * 11. Add flightList
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added a flight list"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/newList")
    public ResponseEntity<String> addFlightList(@Valid @RequestBody List<Flight> flightList){

        flightService.addFlightList(flightList);

        return ResponseEntity.status(HttpStatus.CREATED).body("Flight list added");
    }

}
