package com.hackaboss.agenciaTurismo.service;

import com.hackaboss.agenciaTurismo.dto.ClientDTO;
import com.hackaboss.agenciaTurismo.dto.HotelDTO;
import com.hackaboss.agenciaTurismo.dto.RoomBookingDTO;
import com.hackaboss.agenciaTurismo.dto.RoomDTO;
import com.hackaboss.agenciaTurismo.exception.AlreadyExistEntityException;
import com.hackaboss.agenciaTurismo.exception.CannotBeDeletedBecauseItHasBookingsException;
import com.hackaboss.agenciaTurismo.exception.EntityNotFoundException;
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
import java.time.temporal.ChronoUnit;
import java.util.List;
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

    public static final String ENTITY_HOTEL = "hotel";
    private static final String ENTITY_ROOM = "room";
    public static final String ENTITY_ROOM_BOOKING = "room booking";


    @Override
    public void addHotel(Hotel hotel) {

        List<Hotel> existingHotel = hotelRepository.findByNameAndCityAndNotDeleted(hotel.getName(), hotel.getCity())
                .orElseThrow( () -> new AlreadyExistEntityException(ENTITY_HOTEL));

        hotel.setHotelCode(hotel.getName(), hotel.getCity(), existingHotel.size() + 1);

        hotelRepository.save(hotel);

    }

    @Override
    public List<HotelDTO> getHotels() {

        return hotelRepository.findAllNotDeleted().stream()
                .map(this::toGetHotelDTO)
                .toList();

    }


    @Override
    public void addRoom(Integer hotelId, Room room) {

        Hotel hotel = hotelRepository.findByIdAndNotDeleted(hotelId)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_HOTEL));

        room.setHotel(hotel);
        room.setRoomCode(hotel.getHotelCode(), hotel.getRooms().size() + 1);

        hotel.getRooms().add(room);

        roomRepository.save(room);

        hotelRepository.save(hotel);

    }



    @Override
    public void updateHotel(Integer hotelId, Hotel hotel) {

        Hotel existingHotel = hotelRepository.findByIdAndNotDeleted(hotelId)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_HOTEL) );

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

            Hotel existingHotel = hotelRepository.findByIdAndNotDeleted(hotelId)
                    .orElseThrow(() -> new EntityNotFoundException(ENTITY_HOTEL));

            Room existingRoom = existingHotel.getRooms().stream()
                    .filter(r -> r.getId().equals(roomId))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException(ENTITY_ROOM));

            if(room.getRoomType() != null && !room.getRoomType().isBlank())  existingRoom.setRoomType(room.getRoomType());
            if(room.getRoomPrice() != null && room.getRoomPrice() > 0)  existingRoom.setRoomPrice(room.getRoomPrice());

            roomRepository.save(existingRoom);
    }


    @Override
    public RoomDTO getRoomById(Integer hotelId, Integer roomId) {

         Room room = roomRepository.findByIdAndNotDeleted(roomId)
                .orElseThrow( () -> new EntityNotFoundException(ENTITY_ROOM) );

         List<RoomBooking> filteredRoomBooking = room.getRoomBookingList().stream()
                .filter(roomBooking -> !roomBooking.isDeleted())
                 .toList();

         room.setRoomBookingList(filteredRoomBooking);

        return toRoomDTO(room);

    }

    @Override
    public void deleteHotel(Integer hotelId) {

            Hotel hotel = hotelRepository.findByIdAndNotDeleted(hotelId)
                    .orElseThrow(() -> new EntityNotFoundException(ENTITY_HOTEL));

            boolean bookingExists = hotel.getRooms().stream()
                    .flatMap(room -> room.getRoomBookingList().stream())
                    .anyMatch(roomBooking -> !roomBooking.isCompleted());

            if(bookingExists) throw new CannotBeDeletedBecauseItHasBookingsException("Hotel cannot be deleted because it has bookings.");

            hotel.setDeleted(true);
            hotel.getRooms().forEach(room -> room.setDeleted(true));

            hotelRepository.save(hotel);
    }


    @Override
    public void deleteRoom(Integer hotelId, Integer roomId) {

        Hotel hotel = hotelRepository.findByIdAndNotDeleted(hotelId)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_HOTEL));

        Room room = hotel.getRooms().stream()
                .filter(r -> r.getId().equals(roomId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_ROOM));

        boolean bookingExists = room.getRoomBookingList().stream()
                .anyMatch(roomBooking -> !roomBooking.isCompleted() && !roomBooking.isDeleted());


        if(bookingExists){

            throw new CannotBeDeletedBecauseItHasBookingsException("Room cannot be deleted because it has bookings.");
        }

        room.setDeleted(true);

        roomRepository.save(room);
    }



    @Override
    public List<RoomDTO> findByCityAndDate(String city, LocalDate dateTo, LocalDate dateFrom) {

        return roomRepository.findByCityAndDate(city, dateTo, dateFrom).stream()
                .map(this::toGetRoomDTO)
                .toList();
    }



    @Override
    public Double addRoomBooking(Integer roomId, RoomBookingDTO roomBookingDTO) {

        Room room = roomRepository.findByIdAndNotDeleted(roomId)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_ROOM));

        boolean bookingExist = isBookingExist(room, roomBookingDTO.getDateFrom(), roomBookingDTO.getDateTo());

        if (bookingExist) throw new AlreadyExistEntityException(ENTITY_ROOM_BOOKING);

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

        boolean roomBookedEveryday = isRoomBookedEveryday(roomId, room.getDateFrom(), room.getDateTo());

        room.setBooked(roomBookedEveryday);

        room.getRoomBookingList().add(roomBooking);

        roomRepository.save(room);

        return room.getRoomPrice();

    }


    private boolean isBookingExist(Room room, LocalDate dateFrom, LocalDate dateTo) {

        List<RoomBooking> bookings = roomBookingRepository.findBookingsInDateRange(room.getId(), dateFrom, dateTo);

        return !bookings.isEmpty();

    }



    private boolean isRoomBookedEveryday(Integer roomId, LocalDate dateFrom, LocalDate dateTo) {
        List<RoomBooking> bookings = roomBookingRepository.findBookingsForRoomId(roomId);
        long totalDays = ChronoUnit.DAYS.between(dateFrom, dateTo) + 1; // +1 to include both start and end date
        long bookedDays = bookings.stream()
                .mapToLong(booking -> ChronoUnit.DAYS.between(booking.getDateFrom(), booking.getDateTo()) + 1)
                .sum();
        return bookedDays >= totalDays;
    }


    @Override
    public List<RoomBookingDTO> getRoomBookings() {

        return roomBookingRepository.findIncompleteAndNotDeletedBookings().stream()
                .map(this::toRoomBookingDTO)
                .toList();
    }



    @Override
    public void deleteRoomBooking(Integer roomBookingId) {

        RoomBooking roomBooking = roomBookingRepository.findById(roomBookingId)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_ROOM_BOOKING));

        roomBooking.setDeleted(true);

        roomBookingRepository.save(roomBooking);
    }


    @Override
    public void updateRoomBooking(Integer roomBookingId, RoomBookingDTO roomBookingDTO) {

        RoomBooking roomBooking = roomBookingRepository.findByIdAndNotDeleted(roomBookingId)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_ROOM_BOOKING));

        if(roomBookingDTO.getDateFrom() != null) roomBooking.setDateFrom(roomBookingDTO.getDateFrom());
        if(roomBookingDTO.getDateTo() != null) roomBooking.setDateTo(roomBookingDTO.getDateTo());

        boolean bookingExist = isBookingExist(roomBooking.getRoom(), roomBookingDTO.getDateFrom(), roomBookingDTO.getDateTo());

        if(!bookingExist) {

            roomBookingRepository.save(roomBooking);
            boolean roomBookedEveryday = isRoomBookedEveryday(roomBooking.getRoom().getId(), roomBooking.getRoom().getDateFrom(), roomBooking.getRoom().getDateTo());

            roomBooking.getRoom().setBooked(roomBookedEveryday);
        }

        else throw new AlreadyExistEntityException(ENTITY_ROOM_BOOKING);
    }

    @Override
    public void completeRoomBooking(Integer roomBookingId) {

        RoomBooking roomBooking = roomBookingRepository.findByIdAndNotDeleted(roomBookingId)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_ROOM_BOOKING));

        roomBooking.setCompleted(true);

        roomBookingRepository.save(roomBooking);
    }

    @Override
    public void addHotelList(List<Hotel> hotelList) {

        hotelList.forEach(hotel -> hotel.setHotelCode(hotel.getName(), hotel.getCity(), hotelList.indexOf(hotel) + 1));

        hotelRepository.saveAll(hotelList);
    }

    @Override
    public void addRoomList(Integer hotelId, List<Room> roomList) {

            Hotel hotel = hotelRepository.findByIdAndNotDeleted(hotelId)
                    .orElseThrow(() -> new EntityNotFoundException(ENTITY_HOTEL));

            roomList.forEach(room -> room.setHotel(hotel));

            roomList.forEach(room -> room.setRoomCode(hotel.getHotelCode(), roomList.indexOf(room) + 1));

            roomRepository.saveAll(roomList);

            hotel.getRooms().addAll(roomList);

            hotelRepository.save(hotel);
    }


    @Override
    public HotelDTO getHotelById(Integer hotelId) {

        return hotelRepository.findByIdAndNotDeleted(hotelId).map(this::toHotelDTO).orElse(null);
    }



    private RoomDTO toRoomDTO(Room room){

        return new RoomDTO(room.getId(), room.getRoomType(), room.getRoomPrice(), room.getRoomCode(), room.getDateFrom(),
                room.getDateTo(), room.getRoomBookingList().stream().map(this::toRoomBookingDTO).toList());
    }


    private RoomDTO toGetRoomDTO(Room room){

        return new RoomDTO(room.getId(), room.getRoomType(), room.getRoomPrice(), room.getRoomCode(), room.getDateFrom(),
                room.getDateTo());
    }


    private HotelDTO toHotelDTO(Hotel hotel){

        return new HotelDTO(hotel.getId(), hotel.getName(), hotel.getCity(), hotel.getHotelCode(),
                hotel.getRooms().stream().map(this::toRoomDTO).toList());
    }


    private HotelDTO toGetHotelDTO(Hotel hotel){

        return new HotelDTO(hotel.getId(), hotel.getName(), hotel.getCity(), hotel.getHotelCode());
    }


    private RoomBookingDTO toRoomBookingDTO(RoomBooking roomBooking){

        return new RoomBookingDTO(roomBooking.getBookingCode(), roomBooking.getDateFrom(), roomBooking.getDateTo(),
                roomBooking.getRoom().getHotel().getCity(), roomBooking.getRoom().getHotel().getName(),
                roomBooking.getRoom().getRoomCode(), roomBooking.getRoom().getRoomType(), toClientDTO(roomBooking.getClient()));
    }


    private ClientDTO toClientDTO(Client client){

        return new ClientDTO(client.getName(), client.getLastName(), client.getNif(), client.getEmail());
    }
}
