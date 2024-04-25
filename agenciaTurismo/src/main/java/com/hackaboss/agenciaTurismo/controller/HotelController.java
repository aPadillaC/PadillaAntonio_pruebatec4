package com.hackaboss.agenciaTurismo.controller;

import com.hackaboss.agenciaTurismo.dto.HotelDTO;
import com.hackaboss.agenciaTurismo.dto.RoomBookingDTO;
import com.hackaboss.agenciaTurismo.dto.RoomDTO;
import com.hackaboss.agenciaTurismo.model.Hotel;
import com.hackaboss.agenciaTurismo.model.Room;
import com.hackaboss.agenciaTurismo.service.IHotelService;
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



    // 1. Add hotel
    @PostMapping("/new")
    public ResponseEntity<String> addHotel(@Valid @RequestBody Hotel hotel){

        hotelService.addHotel(hotel);

        return ResponseEntity.status(HttpStatus.CREATED).body("Hotel added");
    }



    // 2. Get hotels
    @GetMapping
    public ResponseEntity<List<HotelDTO>> getHotels(){

        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotels());
    }



    // 3. Find hotel by id
    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDTO> getHotelById(@Positive @NotNull @PathVariable Integer hotelId){

        return ResponseEntity.status(HttpStatus.FOUND).body(hotelService.getHotelById(hotelId));
    }



    // 4. Add room
    @PostMapping("/{hotelId}/rooms/new")
    public ResponseEntity<String> addRoom(@Positive @NotNull @PathVariable Integer hotelId, @Valid @RequestBody Room room){

        hotelService.addRoom(hotelId, room);

        return ResponseEntity.status(HttpStatus.CREATED).body("Room added");
    }



    // 5. Update hotel
    @PutMapping("/edit/{hotelId}")
    public ResponseEntity<String> updateHotel(@Positive @NotNull @PathVariable Integer hotelId, @Valid @RequestBody Hotel hotel){

        hotelService.updateHotel(hotelId, hotel);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Hotel updated");
    }



    // 6. Update room
    @PutMapping("/{hotelId}/rooms/edit/{roomId}")
    public ResponseEntity<String> updateRoom(@Positive @NotNull @PathVariable Integer hotelId,
                             @Positive @PathVariable Integer roomId,
                             @Valid @RequestBody Room room){

        hotelService.updateRoom(hotelId, roomId, room);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Room updated");
    }



    // 7. Get rooms by hotel id
    @GetMapping("/{hotelId}/rooms/{roomId}")
    public ResponseEntity<RoomDTO> getRoomById(@Positive @NotNull @PathVariable Integer hotelId,
                               @Positive @NotNull @PathVariable Integer roomId){

        return ResponseEntity.status(HttpStatus.FOUND).body(hotelService.getRoomById(hotelId, roomId));
    }



    // 8. Delete hotel
    @DeleteMapping("/delete/{hotelId}")
    public ResponseEntity<String> deleteHotel(@Positive @NotNull @PathVariable Integer hotelId){

        hotelService.deleteHotel(hotelId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Hotel deleted");
    }



    // 9. Delete room
    @DeleteMapping("/{hotelId}/rooms/delete/{roomId}")
    public ResponseEntity<String> deleteRoom(@Positive @NotNull @PathVariable Integer hotelId,
                             @Positive @NotNull @PathVariable Integer roomId){

        hotelService.deleteRoom(hotelId, roomId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Room deleted");
    }



    // 10. Find rooms by conditions
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



    // 11. Add room-booking
    @PostMapping("/{roomId}/rooms-booking/new")
    public ResponseEntity<String> addRoomBooking(@Positive @NotNull @PathVariable Integer roomId,
                                 @Valid @RequestBody RoomBookingDTO roomBookingDTO){

        Double price = hotelService.addRoomBooking(roomId, roomBookingDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("Room booking added, total price: " + price + " €");
    }



    // 12. Get room-bookings
    @GetMapping("/rooms-booking")
    public ResponseEntity<List<RoomBookingDTO>> getRoomBookings(){

        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getRoomBookings());
    }



    // 13. Delete room-booking
    @DeleteMapping("/rooms-booking/delete/{roomBookingId}")
    public ResponseEntity<String> deleteRoomBooking(@Positive @NotNull @PathVariable Integer roomBookingId){

        hotelService.deleteRoomBooking(roomBookingId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Room booking deleted");
    }



    // 14. Update room-booking
    @PutMapping("/rooms-booking/edit/{roomBookingId}")
    public ResponseEntity<String> updateRoomBooking(@Positive @NotNull @PathVariable Integer roomBookingId,
                                    @Valid @RequestBody RoomBookingDTO roomBookingDTO){

        hotelService.updateRoomBooking(roomBookingId, roomBookingDTO);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Room booking updated");
    }



    // 15. Complete room-booking
    @PutMapping("/rooms-booking/complete/{roomBookingId}")
    public ResponseEntity<String> completeRoomBooking(@Positive @NotNull @PathVariable Integer roomBookingId){

        hotelService.completeRoomBooking(roomBookingId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Room booking completed");
    }



    // 16. Add hotelList
    @PostMapping("/new-list")
    public ResponseEntity<String> addHotelList(@Valid @RequestBody List<Hotel> hotelList){

        hotelService.addHotelList(hotelList);

        return ResponseEntity.status(HttpStatus.CREATED).body("Hotel list added");
    }



    // 17. Add roomList
    @PostMapping("/{hotelId}/rooms/new-list")
    public ResponseEntity<String> addRoomList(@Positive @NotNull @PathVariable Integer hotelId,
                              @Valid @RequestBody List<Room> roomList){

        hotelService.addRoomList(hotelId, roomList);

        return ResponseEntity.status(HttpStatus.CREATED).body("Room list added");
    }

}
