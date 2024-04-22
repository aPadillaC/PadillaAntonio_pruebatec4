package com.hackaboss.agenciaTurismo.service;

import com.hackaboss.agenciaTurismo.dto.ClientDTO;
import com.hackaboss.agenciaTurismo.dto.HotelDTO;
import com.hackaboss.agenciaTurismo.dto.RoomBookingDTO;
import com.hackaboss.agenciaTurismo.dto.RoomDTO;
import com.hackaboss.agenciaTurismo.model.Client;
import com.hackaboss.agenciaTurismo.model.Hotel;
import com.hackaboss.agenciaTurismo.model.Room;
import com.hackaboss.agenciaTurismo.model.RoomBooking;
import com.hackaboss.agenciaTurismo.repository.ClientRepository;
import com.hackaboss.agenciaTurismo.repository.HotelRepository;
import com.hackaboss.agenciaTurismo.repository.RoomBookingRepository;
import com.hackaboss.agenciaTurismo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class HotelService implements IHotelService{

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;


    @Autowired
    private ClientRepository clientRepository;


    @Autowired
    private RoomBookingRepository roomBookingRepository;


    @Override
    public void addHotel(Hotel hotel) {

        List<Hotel> existingHotel = hotelRepository.findByNameAndCity(hotel.getName(), hotel.getCity());

        if(existingHotel == null) {
            hotel.setHotelCode(hotel.getName(), hotel.getCity(), 1);
        }

        hotel.setHotelCode(hotel.getName(), hotel.getCity(), existingHotel.size() + 1);

        hotelRepository.save(hotel);

    }

    @Override
    public List<HotelDTO> getHotels() {

        return hotelRepository.findAllNotDeleted().stream()
                .map(this::toHotelDTO)
                .collect(Collectors.toList());

    }

    @Override
    public void addRoom(Integer hotelId, Room room) {

        Hotel hotel = hotelRepository.findByIdAndNotDeleted(hotelId).get();

        room.setHotel(hotel);
        room.setRoomCode(hotel.getHotelCode(), hotel.getRooms().size() + 1);

        hotel.getRooms().add(room);

        roomRepository.save(room);

        hotelRepository.save(hotel);

    }


    @Override
    public void updateHotel(Integer hotelId, Hotel hotel) {

        Hotel existingHotel = hotelRepository.findByIdAndNotDeleted(hotelId).get();

        if(hotel.getName() != null && !hotel.getName().isBlank())  existingHotel.setName(hotel.getName());
        if(hotel.getCity() != null && !hotel.getCity().isBlank())  existingHotel.setCity(hotel.getCity());

        existingHotel.setHotelCode(existingHotel.getName(), existingHotel.getCity(), existingHotel.getId());
        updateRoomCode(existingHotel);

        hotelRepository.save(existingHotel);
    }

    public void updateRoomCode(Hotel hotel){

        IntStream.range(0, hotel.getRooms().size())
                .forEach(i -> hotel.getRooms().get(i).setRoomCode(hotel.getHotelCode(), i + 1));
    }

    @Override
    public void updateRoom(Integer hotelId, Integer roomId, Room room) {

            Hotel existingHotel = hotelRepository.findByIdAndNotDeleted(hotelId).get();

            Room existingRoom = existingHotel.getRooms().stream()
                    .filter(r -> r.getId().equals(roomId))
                    .findFirst()
                    .get();

            if(room.getRoomType() != null && !room.getRoomType().isBlank())  existingRoom.setRoomType(room.getRoomType());
            if(room.getRoomPrice() != null && room.getRoomPrice() > 0)  existingRoom.setRoomPrice(room.getRoomPrice());


            roomRepository.save(existingRoom);
    }


    @Override
    public RoomDTO getRoomById(Integer hotelId, Integer roomId) {

        return hotelRepository.findByIdAndNotDeleted(hotelId)
                .map(hotel -> hotel.getRooms().stream()
                    .filter(room -> room.getId().equals(roomId))
                    .map(this::toRoomDTO)
                    .findFirst()
                    .orElse(null))
                .orElse(null);
    }

    @Override
    public void deleteHotel(Integer hotelId) {

            Hotel hotel = hotelRepository.findByIdAndNotDeleted(hotelId).get();

            boolean bookingExists = hotel.getRooms().stream()
                    .flatMap(room -> room.getRoomBookingList().stream())
                    .anyMatch(roomBooking -> !roomBooking.isCompleted());

            //! TODO: Hacer Excepcion
            //if(bookingExists) ;

            hotel.setDeleted(true);
            hotel.getRooms().forEach(room -> room.setDeleted(true));

            hotelRepository.save(hotel);
    }


    @Override
    public void deleteRoom(Integer hotelId, Integer roomId) {

        Hotel hotel = hotelRepository.findByIdAndNotDeleted(hotelId).get();

        Room room = hotel.getRooms().stream()
                .filter(r -> r.getId().equals(roomId))
                .findFirst()
                .get();

        boolean bookingExists = room.getRoomBookingList().stream()
                .anyMatch(roomBooking -> !roomBooking.isCompleted());


        //! TODO: Hacer Excepcion
        //if(bookingExists) ;

        room.setDeleted(true);


    }



    @Override
    public List<RoomDTO> findByCityAndDate(String city, LocalDate dateTo, LocalDate dateFrom) {

        return roomRepository.findByCityAndDate(city, dateTo, dateFrom).stream()
                .map(this::toRoomDTO)
                .collect(Collectors.toList());
    }



    @Override
    public Double addRoomBooking(Integer roomId, RoomBookingDTO roomBookingDTO) {

        Room room = roomRepository.findByIdAndNotDeleted(roomId).get();

        ClientDTO clientDTO = roomBookingDTO.getClient();

        Client existingClient = clientRepository.findByNifAndNotDeleted(clientDTO.getNif());

        Client client = new Client();

        if(existingClient == null) {

            client.setName(clientDTO.getName());
            client.setLastName(clientDTO.getLastName());
            client.setNif(clientDTO.getNif());
            client.setEmail(clientDTO.getEmail());
            clientRepository.save(client);
        }

        else client = existingClient;

        RoomBooking roomBooking = new RoomBooking();

        roomBooking.setDateFrom(roomBookingDTO.getDateFrom());
        roomBooking.setDateTo(roomBookingDTO.getDateTo());
        roomBooking.setClient(client);
        roomBooking.setRoom(room);
        roomBooking.setBookingCode(room.getRoomCode(), room.getRoomBookingList().size() + 1);

        roomBookingRepository.save(roomBooking);

        room.getRoomBookingList().add(roomBooking);

        roomRepository.save(room);

        return room.getRoomPrice();

    }

    @Override
    public List<RoomBookingDTO> getRoomBookings() {

        return roomBookingRepository.findIncompleteAndNotDeletedBookings().stream()
                .map(this::toRoomBookingDTO)
                .collect(Collectors.toList());
    }


    @Override
    public HotelDTO getHotelById(Integer hotelId) {

        return hotelRepository.findByIdAndNotDeleted(hotelId).map(this::toHotelDTO).orElse(null);
    }



    private RoomDTO toRoomDTO(Room room){

        return new RoomDTO(room.getId(), room.getRoomType(), room.getRoomPrice(), room.getRoomCode(), room.getDateFrom(), room.getDateTo(), room.getRoomBookingList());
    }


    private HotelDTO toHotelDTO(Hotel hotel){

        return new HotelDTO(hotel.getId(), hotel.getName(), hotel.getCity(), hotel.getHotelCode(), hotel.getRooms().stream().map(this::toRoomDTO).collect(Collectors.toList()));
    }


    private RoomBookingDTO toRoomBookingDTO(RoomBooking roomBooking){

        return new RoomBookingDTO(roomBooking.getBookingCode(), roomBooking.getDateFrom(), roomBooking.getDateTo(), roomBooking.getRoom().getHotel().getCity(), roomBooking.getRoom().getHotel().getName(), roomBooking.getRoom().getRoomCode(), roomBooking.getRoom().getRoomType(), toClientDTO(roomBooking.getClient()));
    }


    private ClientDTO toClientDTO(Client client){

        return new ClientDTO(client.getName(), client.getLastName(), client.getNif(), client.getEmail());
    }
}
