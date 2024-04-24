package com.hackaboss.agenciaTurismo.controller;

import com.hackaboss.agenciaTurismo.dto.HotelDTO;
import com.hackaboss.agenciaTurismo.dto.RoomBookingDTO;
import com.hackaboss.agenciaTurismo.dto.RoomDTO;
import com.hackaboss.agenciaTurismo.model.Hotel;
import com.hackaboss.agenciaTurismo.model.Room;
import com.hackaboss.agenciaTurismo.model.RoomBooking;
import com.hackaboss.agenciaTurismo.service.IClientService;
import com.hackaboss.agenciaTurismo.service.IHotelService;
import com.hackaboss.agenciaTurismo.service.IRoomBookingService;
import com.hackaboss.agenciaTurismo.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("agency/hotels")
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IRoomBookingService roomBookingService;

    @Autowired
    private IClientService clientService;



    // 1. Add hotel
    @PostMapping("/new")
    public String addHotel(@RequestBody Hotel hotel){

        hotelService.addHotel(hotel);

        return "Hotel added";
    }


    // 2. Get hotels
    @GetMapping
    public List<HotelDTO> getHotels(){

        return hotelService.getHotels();
    }


    // 3. Find hotel by id
    @GetMapping("/{hotelId}")
    public HotelDTO getHotelById(@PathVariable Integer hotelId){

        return hotelService.getHotelById(hotelId);
    }


    // 4. Add room
    @PostMapping("/{hotelId}/rooms/new")
    public String addRoom(@PathVariable Integer hotelId, @RequestBody Room room){

        hotelService.addRoom(hotelId, room);

        return "Room added";
    }


    // 5. Update hotel
    @PutMapping("/edit/{hotelId}")
    public String updateHotel(@PathVariable Integer hotelId, @RequestBody Hotel hotel){

        hotelService.updateHotel(hotelId, hotel);

        return "Hotel updated";
    }


    // 6. Update room
    @PutMapping("/{hotelId}/rooms/edit/{roomId}")
    public String updateRoom(@PathVariable Integer hotelId, @PathVariable Integer roomId, @RequestBody Room room){

        hotelService.updateRoom(hotelId, roomId, room);

        return "Room updated";
    }


    // 7. Get rooms by hotel id
    @GetMapping("/{hotelId}/rooms/{roomId}")
    public RoomDTO getRoomById(@PathVariable Integer hotelId, @PathVariable Integer roomId){

        return hotelService.getRoomById(hotelId, roomId);
    }


    // 8. Delete hotel
    @DeleteMapping("/delete/{hotelId}")
    public String deleteHotel(@PathVariable Integer hotelId){

        hotelService.deleteHotel(hotelId);
        return "Hotel deleted";
    }


    // 9. Delete room
    @DeleteMapping("/{hotelId}/rooms/delete/{roomId}")
    public String deleteRoom(@PathVariable Integer hotelId, @PathVariable Integer roomId){

        hotelService.deleteRoom(hotelId, roomId);
        return "Room deleted";
    }


    // 10. Find rooms by conditions
    @GetMapping("/rooms")
    public List<RoomDTO> getRoomsByCityAndDate(@RequestParam("city") String city,
                                               @RequestParam("dateTo") LocalDate dateTo,
                                               @RequestParam("dateFrom") LocalDate dateFrom) {

        return hotelService.findByCityAndDate(city, dateTo, dateFrom);
    }



    // 11. Add room-booking
    @PostMapping("/{roomId}/rooms-booking/new")
    public String addRoomBooking(@PathVariable Integer roomId,
                                @RequestBody RoomBookingDTO roomBookingDTO){

        Double price = hotelService.addRoomBooking(roomId, roomBookingDTO);

        return "Room booking added, price: " + price + " €";
    }



    // 12. Get room-bookings
    @GetMapping("/rooms-booking")
    public List<RoomBookingDTO> getRoomBookings(){

        return hotelService.getRoomBookings();
    }


    // 13. Delete room-booking
    @DeleteMapping("/rooms-booking/delete/{roomBookingId}")
    public String deleteRoomBooking(@PathVariable Integer roomBookingId){

        hotelService.deleteRoomBooking(roomBookingId);

        return "Room booking deleted";
    }



    // 14. Update room-booking
    @PutMapping("/rooms-booking/edit/{roomBookingId}")
    public String updateRoomBooking(@PathVariable Integer roomBookingId, @RequestBody RoomBookingDTO roomBookingDTO){

        hotelService.updateRoomBooking(roomBookingId, roomBookingDTO);

        return "Room booking updated";
    }


    // 15. Complete room-booking
    @PutMapping("/rooms-booking/complete/{roomBookingId}")
    public String completeRoomBooking(@PathVariable Integer roomBookingId){

        hotelService.completeRoomBooking(roomBookingId);

        return "Room booking completed";
    }


    // 16. Add hotelList
    @PostMapping("/new-list")
    public String addHotelList(@RequestBody List<Hotel> hotelList){

        hotelService.addHotelList(hotelList);

        return "Hotel list added";
    }


    // 17. Add roomList
    @PostMapping("/{hotelId}/rooms/new-list")
    public String addRoomList(@PathVariable Integer hotelId, @RequestBody List<Room> roomList){

        hotelService.addRoomList(hotelId, roomList);

        return "Room list added";
    }



}
