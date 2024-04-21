package com.hackaboss.agenciaTurismo.controller;

import com.hackaboss.agenciaTurismo.dto.HotelDTO;
import com.hackaboss.agenciaTurismo.dto.RoomDTO;
import com.hackaboss.agenciaTurismo.model.Hotel;
import com.hackaboss.agenciaTurismo.model.Room;
import com.hackaboss.agenciaTurismo.service.IClientService;
import com.hackaboss.agenciaTurismo.service.IHotelService;
import com.hackaboss.agenciaTurismo.service.IRoomBookingService;
import com.hackaboss.agenciaTurismo.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    // 3. Add hotel by id
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

    // 10. Add room-booking


}
