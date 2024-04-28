package com.hackaboss.agenciaTurismo.service;


import com.hackaboss.agenciaTurismo.dto.ClientDTO;
import com.hackaboss.agenciaTurismo.dto.HotelDTO;
import com.hackaboss.agenciaTurismo.dto.RoomBookingDTO;
import com.hackaboss.agenciaTurismo.exception.EntityNotFoundException;
import com.hackaboss.agenciaTurismo.exception.ParameterConflictException;
import com.hackaboss.agenciaTurismo.model.Client;
import com.hackaboss.agenciaTurismo.model.Hotel;
import com.hackaboss.agenciaTurismo.model.Room;
import com.hackaboss.agenciaTurismo.dto.RoomDTO;
import com.hackaboss.agenciaTurismo.model.RoomBooking;
import com.hackaboss.agenciaTurismo.repository.ClientRepository;
import com.hackaboss.agenciaTurismo.repository.HotelRepository;
import com.hackaboss.agenciaTurismo.repository.RoomBookingRepository;
import com.hackaboss.agenciaTurismo.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {

    @InjectMocks
    private HotelService hotelServiceInyectado;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private RoomBookingRepository roomBookingRepository;


    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetHotels() {

        // Prepare test data
        Hotel hotel1 = new Hotel();
        hotel1.setId(1);
        hotel1.setName("Hotel Test 1");
        hotel1.setCity("Test City 1");
        hotel1.setHotelCode("HT1");

        Hotel hotel2 = new Hotel();
        hotel2.setId(2);
        hotel2.setName("Hotel Test 2");
        hotel2.setCity("Test City 2");
        hotel2.setHotelCode("HT2");

        List<Hotel> hotels = Arrays.asList(hotel1, hotel2);

        // Configure the behaviour of the mocked repository
        when(hotelRepository.findAllNotDeleted()).thenReturn(hotels);

        // Call the getHotels method
        List<HotelDTO> hotelDTOs = hotelServiceInyectado.getHotels();

        // Verify that the values in the HotelDTO list are as expected
        assertEquals(hotels.size(), hotelDTOs.size());
        for (int i = 0; i < hotels.size(); i++) {
            assertEquals(hotels.get(i).getId(), hotelDTOs.get(i).getId());
            assertEquals(hotels.get(i).getName(), hotelDTOs.get(i).getName());
            assertEquals(hotels.get(i).getCity(), hotelDTOs.get(i).getCity());
            assertEquals(hotels.get(i).getHotelCode(), hotelDTOs.get(i).getHotelCode());
        }
    }



    @Test
    public void testGetHotelsEx() {


        List<Hotel> hotels = new ArrayList<>();

        when(hotelRepository.findAllNotDeleted()).thenReturn(hotels);

        assertThrows(EntityNotFoundException.class, () -> hotelServiceInyectado.getHotels());
    }



    @Test
    public void testFindByCityAndDate() {

        String city = "Test City";
        LocalDate dateFrom = LocalDate.of(2023, 1, 1);
        LocalDate dateTo = LocalDate.of(2023, 12, 31);

        Room room1 = new Room();
        room1.setId(1);
        room1.setRoomType("Deluxe");
        room1.setRoomPrice(100.0);
        room1.setRoomCode("S1");
        room1.setDateFrom(LocalDate.of(2023, 1, 1));
        room1.setDateTo(LocalDate.of(2023, 12, 31));

        Room room2 = new Room();
        room2.setId(2);
        room2.setRoomType("Double");
        room2.setRoomPrice(200.0);
        room2.setRoomCode("D1");
        room2.setDateFrom(LocalDate.of(2023, 1, 1));
        room2.setDateTo(LocalDate.of(2023, 12, 31));

        List<Room> rooms = Arrays.asList(room1, room2);

        when(roomRepository.findByCityAndDate(city, dateTo, dateFrom)).thenReturn(Optional.of(rooms));


        List<RoomDTO> roomDTOs = hotelServiceInyectado.findByCityAndDate(city, dateFrom, dateTo);

        assertEquals(rooms.size(), roomDTOs.size());
        for (int i = 0; i < rooms.size(); i++) {
            assertEquals(rooms.get(i).getId(), roomDTOs.get(i).getId());
            assertEquals(rooms.get(i).getRoomType(), roomDTOs.get(i).getRoomType());
            assertEquals(rooms.get(i).getRoomPrice(), roomDTOs.get(i).getRoomPrice());
            assertEquals(rooms.get(i).getRoomCode(), roomDTOs.get(i).getRoomCode());
            assertEquals(rooms.get(i).getDateFrom(), roomDTOs.get(i).getDateFrom());
            assertEquals(rooms.get(i).getDateTo(), roomDTOs.get(i).getDateTo());
        }
    }



    @Test
    public void testFindByCityAndDateEx() {

        String city = "Test City";
        LocalDate dateFrom = LocalDate.of(2023, 1, 1);
        LocalDate dateTo = LocalDate.of(2023, 12, 31);

        List<Room> rooms = new ArrayList<>();

        when(roomRepository.findByCityAndDate(city, dateTo, dateFrom)).thenReturn(Optional.of(rooms));

        assertThrows(EntityNotFoundException.class, () -> hotelServiceInyectado.findByCityAndDate(city, dateFrom, dateTo));
    }



    @Test
    public void testAddRoomBooking() {

        Integer roomId = 1;
        RoomBookingDTO roomBookingDTO = new RoomBookingDTO();
        roomBookingDTO.setDateFrom(LocalDate.of(2023, 1, 1));
        roomBookingDTO.setDateTo(LocalDate.of(2023, 12, 31));
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Test name");
        clientDTO.setLastName("Test LastName");
        clientDTO.setNif("12345678A");
        clientDTO.setEmail("test@test.com");
        roomBookingDTO.setClient(clientDTO);

        Room room = new Room();
        room.setId(roomId);
        room.setRoomType("Deluxe");
        room.setRoomPrice(100.0);
        room.setRoomCode("S1");
        room.setDateFrom(LocalDate.of(2023, 1, 1));
        room.setDateTo(LocalDate.of(2023, 12, 31));
        room.setRoomBookingList(new ArrayList<>());

        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setLastName(clientDTO.getLastName());
        client.setNif(clientDTO.getNif());
        client.setEmail(clientDTO.getEmail());


        when(roomRepository.findByIdAndNotDeleted(roomId)).thenReturn(Optional.of(room));
        when(clientRepository.findByNifAndNotDeleted(clientDTO.getNif())).thenReturn(client);

        Double result = hotelServiceInyectado.addRoomBooking(roomId, roomBookingDTO);

        Double expected = room.getRoomPrice() * (roomBookingDTO.getDateTo().toEpochDay() - roomBookingDTO.getDateFrom().toEpochDay());
        assertEquals(expected, result);
    }



    @Test
    public void testAddRoomBookingEx() {

        Integer roomId = 1;
        RoomBookingDTO roomBookingDTO = new RoomBookingDTO();
        roomBookingDTO.setDateFrom(LocalDate.of(2023, 1, 1));
        roomBookingDTO.setDateTo(LocalDate.of(2023, 12, 31));
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Test name");
        clientDTO.setLastName("Test LastName");
        clientDTO.setNif("12345678A");
        clientDTO.setEmail("test@test.com");
        roomBookingDTO.setClient(clientDTO);

        Room room = new Room();
        room.setId(roomId);
        room.setRoomType("Deluxe");
        room.setRoomPrice(100.0);
        room.setRoomCode("S1");
        room.setDateFrom(LocalDate.of(2023, 1, 1));
        room.setDateTo(LocalDate.of(2023, 12, 31));
        room.setRoomBookingList(new ArrayList<>());
        room.setBooked(true);

        when(roomRepository.findByIdAndNotDeleted(roomId)).thenReturn(Optional.of(room));

        assertThrows(ParameterConflictException.class, () -> hotelServiceInyectado.addRoomBooking(roomId, roomBookingDTO));
    }



    @Test
    public void testToHotelDTO() {


        Hotel hotel = new Hotel();
        hotel.setId(1);
        hotel.setName("Hotel Test");
        hotel.setCity("Test City");
        hotel.setHotelCode("HT1");

        List<Room> roomList = new ArrayList<>();

        hotel.setRooms(roomList);

        HotelDTO hotelDTO = hotelServiceInyectado.toHotelDTO(hotel);

        assertEquals(hotel.getId(), hotelDTO.getId());
        assertEquals(hotel.getName(), hotelDTO.getName());
        assertEquals(hotel.getCity(), hotelDTO.getCity());
        assertEquals(hotel.getHotelCode(), hotelDTO.getHotelCode());
        assertEquals(hotel.getRooms().size(), hotelDTO.getRooms().size());
    }



    @Test
    public void testToGetHotelDTO() {


        Hotel hotel = new Hotel();
        hotel.setId(1);
        hotel.setName("Hotel Test");
        hotel.setCity("Test City");
        hotel.setHotelCode("HT1");

        HotelDTO hotelDTO = hotelServiceInyectado.toGetHotelDTO(hotel);

        assertEquals(hotel.getId(), hotelDTO.getId());
        assertEquals(hotel.getName(), hotelDTO.getName());
        assertEquals(hotel.getCity(), hotelDTO.getCity());
        assertEquals(hotel.getHotelCode(), hotelDTO.getHotelCode());
    }



    @Test
    public void testToGetRoomDTO() {

        Room room = new Room();
        room.setId(1);
        room.setRoomType("Deluxe");
        room.setRoomPrice(100.0);
        room.setRoomCode("S1");
        room.setDateFrom(LocalDate.of(2023, 1, 1));
        room.setDateTo(LocalDate.of(2023, 12, 31));

        RoomDTO roomDTO = hotelServiceInyectado.toGetRoomDTO(room);

        assertEquals(room.getId(), roomDTO.getId());
        assertEquals(room.getRoomType(), roomDTO.getRoomType());
        assertEquals(room.getRoomPrice(), roomDTO.getRoomPrice());
        assertEquals(room.getRoomCode(), roomDTO.getRoomCode());
        assertEquals(room.getDateFrom(), roomDTO.getDateFrom());
        assertEquals(room.getDateTo(), roomDTO.getDateTo());
    }



    @Test
    public void testToRoomDTO() {

        Room room = new Room();
        room.setId(1);
        room.setRoomType("Deluxe");
        room.setRoomPrice(100.0);
        room.setRoomCode("S1");
        room.setDateFrom(LocalDate.of(2023, 1, 1));
        room.setDateTo(LocalDate.of(2023, 12, 31));

        List<RoomBooking> roomBookingList = new ArrayList<>();

        room.setRoomBookingList(roomBookingList);

        RoomDTO roomDTO = hotelServiceInyectado.toRoomDTO(room);

        assertEquals(room.getId(), roomDTO.getId());
        assertEquals(room.getRoomType(), roomDTO.getRoomType());
        assertEquals(room.getRoomPrice(), roomDTO.getRoomPrice());
        assertEquals(room.getRoomCode(), roomDTO.getRoomCode());
        assertEquals(room.getDateFrom(), roomDTO.getDateFrom());
        assertEquals(room.getDateTo(), roomDTO.getDateTo());
        assertEquals(room.getRoomBookingList().size(), roomDTO.getRoomBookingList().size());
    }



    @Test
    public void testToRoomBookingDTO() {

        RoomBooking roomBooking = new RoomBooking();
        roomBooking.setBookingCode("RB1");
        roomBooking.setDateFrom(LocalDate.of(2023, 1, 1));
        roomBooking.setDateTo(LocalDate.of(2023, 12, 31));
        roomBooking.setClient(new Client());

        Room room = new Room();
        room.setId(1);
        room.setRoomType("Single");
        room.setRoomPrice(100.0);
        room.setRoomCode("S1");
        room.setDateFrom(LocalDate.of(2023, 1, 1));
        room.setDateTo(LocalDate.of(2023, 12, 31));

        Hotel hotel = new Hotel();
        hotel.setId(1);
        hotel.setName("Hotel Test");
        hotel.setCity("Test City");
        hotel.setHotelCode("HT1");

        room.setHotel(hotel);

        roomBooking.setRoom(room);

        RoomBookingDTO roomBookingDTO = hotelServiceInyectado.toRoomBookingDTO(roomBooking);

        assertEquals(roomBooking.getBookingCode(), roomBookingDTO.getBookingCode());
        assertEquals(roomBooking.getDateFrom(), roomBookingDTO.getDateFrom());
        assertEquals(roomBooking.getDateTo(), roomBookingDTO.getDateTo());
    }



    @Test
    public void testToClientDTO() {

        Client client = new Client();
        client.setName("Test Name");
        client.setLastName("Test Lastname");
        client.setNif("12345678A");
        client.setEmail("test@test.com");

        ClientDTO clientDTO = hotelServiceInyectado.toClientDTO(client);

        assertEquals(client.getName(), clientDTO.getName());
        assertEquals(client.getLastName(), clientDTO.getLastName());
        assertEquals(client.getNif(), clientDTO.getNif());
        assertEquals(client.getEmail(), clientDTO.getEmail());
    }

}