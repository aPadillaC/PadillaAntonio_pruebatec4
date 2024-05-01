package com.hackaboss.agenciaTurismo.controller;

import com.hackaboss.agenciaTurismo.dto.HotelDTO;
import com.hackaboss.agenciaTurismo.dto.RoomBookingDTO;
import com.hackaboss.agenciaTurismo.dto.RoomDTO;
import com.hackaboss.agenciaTurismo.model.Hotel;
import com.hackaboss.agenciaTurismo.model.Room;
import com.hackaboss.agenciaTurismo.service.IHotelService;
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
@RequestMapping("agency/hotels")
public class HotelController {

    @Autowired
    private IHotelService hotelService;



    /**
     * 1. Add hotel
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added a hotel"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/new")
    public ResponseEntity<String> addHotel(@Valid @RequestBody Hotel hotel){

        hotelService.addHotel(hotel);

        return ResponseEntity.status(HttpStatus.CREATED).body("Hotel added");
    }



    /**
     * 2. Get hotels
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of hotels"),
            @ApiResponse(responseCode = "404", description = "Hotels not found")
    })
    @GetMapping
    public ResponseEntity<List<HotelDTO>> getHotels(){

        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotels());
    }



    /**
     * 3. Find hotel by id
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Hotel found"),
            @ApiResponse(responseCode = "404", description = "Hotel not found")
    })
    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDTO> getHotelById(@Positive @NotNull @PathVariable Integer hotelId){

        return ResponseEntity.status(HttpStatus.FOUND).body(hotelService.getHotelById(hotelId));
    }



    /**
     * 4. Add room
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added a room"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/{hotelId}/rooms/new")
    public ResponseEntity<String> addRoom(@Positive @NotNull @PathVariable Integer hotelId, @Valid @RequestBody Room room){

        hotelService.addRoom(hotelId, room);

        return ResponseEntity.status(HttpStatus.CREATED).body("Room added");
    }



    /**
     * 5. Update hotel
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Hotel updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/edit/{hotelId}")
    public ResponseEntity<String> updateHotel(@Positive @NotNull @PathVariable Integer hotelId, @Valid @RequestBody Hotel hotel){

        hotelService.updateHotel(hotelId, hotel);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Hotel updated");
    }



    /**
     * 6. Update room
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Room updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{hotelId}/rooms/edit/{roomId}")
    public ResponseEntity<String> updateRoom(@Positive @NotNull @PathVariable Integer hotelId,
                             @Positive @PathVariable Integer roomId,
                             @Valid @RequestBody Room room){

        hotelService.updateRoom(hotelId, roomId, room);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Room updated");
    }



    /**
     * 7. Get rooms by hotel id
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of rooms"),
            @ApiResponse(responseCode = "404", description = "Rooms not found")
    })
    @GetMapping("/{hotelId}/rooms/{roomId}")
    public ResponseEntity<RoomDTO> getRoomById(@Positive @NotNull @PathVariable Integer hotelId,
                               @Positive @NotNull @PathVariable Integer roomId){

        return ResponseEntity.status(HttpStatus.FOUND).body(hotelService.getRoomById(hotelId, roomId));
    }



    /**
     * 8. Delete hotel
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Hotel deleted"),
            @ApiResponse(responseCode = "404", description = "Hotel not found")
    })
    @DeleteMapping("/delete/{hotelId}")
    public ResponseEntity<String> deleteHotel(@Positive @NotNull @PathVariable Integer hotelId){

        hotelService.deleteHotel(hotelId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Hotel deleted");
    }



    /**
     * 9. Delete room
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Room deleted"),
            @ApiResponse(responseCode = "404", description = "Room not found")
    })
    @DeleteMapping("/{hotelId}/rooms/delete/{roomId}")
    public ResponseEntity<String> deleteRoom(@Positive @NotNull @PathVariable Integer hotelId,
                             @Positive @NotNull @PathVariable Integer roomId){

        hotelService.deleteRoom(hotelId, roomId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Room deleted");
    }



    /**
     * 10. Find rooms by city and date
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of rooms"),
            @ApiResponse(responseCode = "404", description = "Rooms not found")
    })
    @GetMapping("/rooms")
    public ResponseEntity<List<RoomDTO>> getRoomsByCityAndDate(@RequestParam("city") String city,
                                               @DateTimeFormat(
                                                       iso = DateTimeFormat.ISO.DATE,
                                                       fallbackPatterns = {"yyy/MM/dd", "dd-MM-yy", "dd/MM/yyy"})
                                                    @RequestParam("dateTo") LocalDate dateTo,
                                               @DateTimeFormat(
                                                       iso = DateTimeFormat.ISO.DATE,
                                                       fallbackPatterns = {"yyy/MM/dd", "dd-MM-yy", "dd/MM/yyy"})
                                                   @RequestParam("dateFrom") LocalDate dateFrom) {

        return ResponseEntity.status(HttpStatus.OK).body(hotelService.findByCityAndDate(city, dateFrom, dateTo));
    }



    /**
     * 11. Add room booking
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added a room booking"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/{roomId}/rooms-booking/new")
    public ResponseEntity<String> addRoomBooking(@Positive @NotNull @PathVariable Integer roomId,
                                 @Valid @RequestBody RoomBookingDTO roomBookingDTO){

        Double price = hotelService.addRoomBooking(roomId, roomBookingDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("Room booking added, total price: " + price + " €");
    }



    /**
     * 12. Get all room bookings
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of room bookings"),
            @ApiResponse(responseCode = "404", description = "Room bookings not found")
    })
    @GetMapping("/rooms-booking")
    public ResponseEntity<List<RoomBookingDTO>> getRoomBookings(){

        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getRoomBookings());
    }



    /**
     * 13. Delete room booking
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Room booking deleted"),
            @ApiResponse(responseCode = "404", description = "Room booking not found")
    })
    @DeleteMapping("/rooms-booking/delete/{roomBookingId}")
    public ResponseEntity<String> deleteRoomBooking(@Positive @NotNull @PathVariable Integer roomBookingId){

        hotelService.deleteRoomBooking(roomBookingId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Room booking deleted");
    }



    /**
     * 14. Update room booking
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Room booking updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/rooms-booking/edit/{roomBookingId}")
    public ResponseEntity<String> updateRoomBooking(@Positive @NotNull @PathVariable Integer roomBookingId,
                                    @Valid @RequestBody RoomBookingDTO roomBookingDTO){

        hotelService.updateRoomBooking(roomBookingId, roomBookingDTO);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Room booking updated");
    }



    /**
     * 15. Complete room booking
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Room booking completed"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/rooms-booking/complete/{roomBookingId}")
    public ResponseEntity<String> completeRoomBooking(@Positive @NotNull @PathVariable Integer roomBookingId){

        hotelService.completeRoomBooking(roomBookingId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Room booking completed");
    }



    /**
     * 16. Add a list of hotels
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added a list of hotels"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/new-list")
    public ResponseEntity<String> addHotelList(@Valid @RequestBody List<Hotel> hotelList){

        hotelService.addHotelList(hotelList);

        return ResponseEntity.status(HttpStatus.CREATED).body("Hotel list added");
    }



    /**
     * 17. Add a list of rooms to a hotel
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added a list of rooms"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/{hotelId}/rooms/new-list")
    public ResponseEntity<String> addRoomList(@Positive @NotNull @PathVariable Integer hotelId,
                              @Valid @RequestBody List<Room> roomList){

        hotelService.addRoomList(hotelId, roomList);

        return ResponseEntity.status(HttpStatus.CREATED).body("Room list added");
    }



    /**
     * 18. Get room bookings by hotel id
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of room bookings"),
            @ApiResponse(responseCode = "404", description = "Room bookings not found")
    })
    @GetMapping("/{hotelId}/rooms-booking")
    public ResponseEntity<List<RoomBookingDTO>> getRoomBookingsByHotelId(@Positive @NotNull @PathVariable Integer hotelId){

        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getRoomBookingsByHotelId(hotelId));
    }

}
