package com.hackaboss.agenciaTurismo.controller;

import com.hackaboss.agenciaTurismo.model.Hotel;
import com.hackaboss.agenciaTurismo.model.Room;
import com.hackaboss.agenciaTurismo.service.IFlightService;
import com.hackaboss.agenciaTurismo.service.IHotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.verify;

public class HotelControllerTest {

    @InjectMocks
    private HotelController hotelController;

    @Mock
    private IHotelService hotelService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testAddHotel() {
        // given
        Hotel hotel = new Hotel();
        hotel.setHotelCode("HC123");
        hotel.setName("Hotel Madrid");
        hotel.setCity("Madrid");

        // when
        hotelController.addHotel(hotel);

        // then
        verify(hotelService).addHotel(hotel);
    }



    @Test
    void testGetHotels() {
        // given
        hotelController.getHotels();

        // then
        verify(hotelService).getHotels();
    }



    @Test
    void testGetHotel() {
        // given
        Integer hotelId = 1;
        hotelController.getHotelById(hotelId);

        // then
        verify(hotelService).getHotelById(hotelId);
    }



    @Test
    void testEditHotel() {
        // given
        Integer hotelId = 1;
        Hotel hotel = new Hotel();
        hotel.setHotelCode("HC123");
        hotel.setName("Hotel Madrid");
        hotel.setCity("Madrid");

        // when
        hotelController.updateHotel(hotelId, hotel);

        // then
        verify(hotelService).updateHotel(hotelId, hotel);
    }



    @Test
    void testDeleteHotel() {
        // given
        Integer hotelId = 1;
        hotelController.deleteHotel(hotelId);

        // then
        verify(hotelService).deleteHotel(hotelId);
    }



    @Test
    void testAddRoom() {
        // given
        Integer hotelId = 1;
        Room room = new Room();
        room.setRoomCode("RC123");
        room.setRoomType("Single");
        room.setRoomPrice(100.0);

        // when
        hotelController.addRoom(hotelId, room);

        // then
        verify(hotelService).addRoom(hotelId, room);
    }



    @Test
    void testDeleteRoom() {
        // given
        Integer hotelId = 1;
        Integer roomId = 1;
        hotelController.deleteRoom(hotelId, roomId);

        // then
        verify(hotelService).deleteRoom(hotelId, roomId);
    }



    @Test
    void testGetRooms() {
        // given
        Integer hotelId = 1;
        hotelController.getRoomById(hotelId, 1);

        // then
        verify(hotelService).getRoomById(hotelId, 1);
    }
}
