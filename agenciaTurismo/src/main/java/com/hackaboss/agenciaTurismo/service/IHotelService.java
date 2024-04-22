package com.hackaboss.agenciaTurismo.service;

import com.hackaboss.agenciaTurismo.dto.HotelDTO;
import com.hackaboss.agenciaTurismo.dto.RoomBookingDTO;
import com.hackaboss.agenciaTurismo.dto.RoomDTO;
import com.hackaboss.agenciaTurismo.model.Hotel;
import com.hackaboss.agenciaTurismo.model.Room;
import com.hackaboss.agenciaTurismo.model.RoomBooking;

import java.time.LocalDate;
import java.util.List;

public interface IHotelService {
    void addHotel(Hotel hotel);

    List<HotelDTO> getHotels();

    void addRoom(Integer hotelId, Room room);

    HotelDTO getHotelById(Integer hotelId);

    void updateHotel(Integer hotelId, Hotel hotel);

    void updateRoom(Integer hotelId, Integer roomId, Room room);

    RoomDTO getRoomById(Integer hotelId, Integer roomId);

    void deleteHotel(Integer hotelId);

    void deleteRoom(Integer hotelId, Integer roomId);

    List<RoomDTO> findByCityAndDate(String city, LocalDate dateTo, LocalDate dateFrom);

    Double addRoomBooking(Integer roomId, RoomBookingDTO roomBookingDTO);

    List<RoomBookingDTO> getRoomBookings();
}
