package com.hackaboss.agenciaTurismo.service;

import com.hackaboss.agenciaTurismo.dto.ClientDTO;
import com.hackaboss.agenciaTurismo.dto.FlightBookingDTO;
import com.hackaboss.agenciaTurismo.dto.FlightDTO;
import com.hackaboss.agenciaTurismo.exception.FlightAlreadyExistsException;
import com.hackaboss.agenciaTurismo.model.*;
import com.hackaboss.agenciaTurismo.repository.FlightBookingRepository;
import com.hackaboss.agenciaTurismo.repository.FlightRepository;
import com.hackaboss.agenciaTurismo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService implements IFlightService{

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ClientRepository clientRepository;


    @Autowired
    private FlightBookingRepository flightBookingRepository;



    @Override
    public void addFlight(Flight flight) {

        //!TODO: hacer excepcion
        List<Flight> existingFlight = flightRepository.findByOriginAndDestinationAndDateAndNotDeleted(flight.getOrigin(), flight.getDestination(), flight.getDate());

        if (!existingFlight.isEmpty()) {

            throw new FlightAlreadyExistsException("A flight with the same characteristics already exists.");

        }

        flight.setFlightCode(flight.getOrigin(), flight.getDestination(), existingFlight.size() + 1);

        flightRepository.save(flight);
    }


    @Override
    public List<FlightDTO> getFlights() {

        return flightRepository.findAllNotDeleted().stream()
                .map(this::toFlightDTO)
                .toList();
    }


    @Override
    public FlightDTO getFlightById(Integer flightId) {
        //!TODO: hacer excepcion personalizada
        return flightRepository.findFlightByIdAndNotDeleted(flightId)
                .map(this::toFlightDTO)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
    }



    //!TODO: hacer excepcion
    @Override
    public void updateFlight(Integer flightId, Flight flight) {

        Flight existingFlight = flightRepository.findFlightByIdAndNotDeleted(flightId).get();

        if(flight.getOrigin() != null && !flight.getOrigin().isBlank()) existingFlight.setOrigin(flight.getOrigin());
        if(flight.getDestination() != null && !flight.getDestination().isBlank()) existingFlight.setDestination(flight.getDestination());
        if(flight.getDate() != null) existingFlight.setDate(flight.getDate());
        if(flight.getAvailableSeats() != null) existingFlight.setAvailableSeats(flight.getAvailableSeats());

        existingFlight.setFlightCode(existingFlight.getOrigin(), existingFlight.getDestination(), existingFlight.getId());

        flightRepository.save(existingFlight);

    }



    @Override
    public void deleteFlight(Integer flightId) {

        Flight flight = flightRepository.findFlightByIdAndNotDeleted(flightId).get();

        boolean bookingExists = flight.getFlightBookingList().stream()
                .anyMatch(booking -> !booking.isDeleted());

        //! TODO: Hacer Excepcion
        if (bookingExists) {
            throw new RuntimeException("Flight cannot be deleted because it has bookings.");
        }

        flight.setDeleted(true);

        flightRepository.save(flight);
    }

    @Override
    public Double addFlightBooking(Integer flightId, FlightBookingDTO flightBookingDTO) {

        List<ClientDTO> clientDTOList = flightBookingDTO.getClientList();

        List<Client> clientList = new ArrayList<>();

        clientDTOList.forEach(clientDTO -> {

            //Filtramos por dni y que no esté eliminado

            Client existingClient = clientRepository.findByNifAndNotDeleted(clientDTO.getNif());

            Client client = new Client();

            if(existingClient == null) {

                client.setName(clientDTO.getName());
                client.setLastName(clientDTO.getLastName());
                client.setNif(clientDTO.getNif());
                client.setEmail(clientDTO.getEmail());

                clientRepository.save(client);

                clientList.add(client);
            }

            else clientList.add(existingClient);
        });

        //!TODO: hacer excepcion

        Flight flightExist = flightRepository.findFlightByIdAndNotDeleted(flightId).get();

        FlightBooking flightBooking = new FlightBooking();

        if (flightExist.getAvailableSeats() == 0) {

            throw new RuntimeException("No available seats");
        }

        flightBooking.setFlight(flightExist);
        flightBooking.setBookingCode(flightExist.getFlightCode(), flightExist.getFlightBookingList().size() + 1);
        flightBooking.setSeatType(flightBookingDTO.getSeatType());
        flightBooking.setSeatPrice(flightBookingDTO.getSeatPrice());
        flightBooking.setClientList(clientList);

        List<FlightBooking> flightExistBookings = flightExist.getFlightBookingList().stream()
                .filter(booking -> !booking.isDeleted())
                .toList();

        if(!flightExistBookings.isEmpty()) {

            boolean bookingExists = isBookingExists(flightExistBookings, flightBooking);

            if (bookingExists) {
                throw new RuntimeException("Booking already exists");
            }
        }

        flightBookingRepository.save(flightBooking);

        flightExist.getFlightBookingList().add(flightBooking);
        flightExist.upDateAvailableSeats(flightBooking);

        flightRepository.save(flightExist);

        return flightBooking.getSeatPrice() * flightBooking.getClientList().size();

    }


    private boolean isBookingExists(List<FlightBooking> flightExistBooking, FlightBooking flightBooking) {

        return flightExistBooking.stream()
                .anyMatch(booking -> booking.getSeatType().equals(flightBooking.getSeatType()) &&
                        booking.getSeatPrice().equals(flightBooking.getSeatPrice()) &&
                        booking.getClientList().containsAll(flightBooking.getClientList()) &&
                        booking.getFlight().equals(flightBooking.getFlight()));

    }




    @Override
    public List<FlightBookingDTO> getFlightBookingForFlightById(Integer flightId) {
        //!TODO: hacer excepcion
        return flightRepository.findFlightByIdAndNotDeleted(flightId).stream()
                .flatMap(flight -> flight.getFlightBookingList().stream()
                        .filter(booking -> !booking.isDeleted())
                        .map(this::toFlightBookingDTO))
                .toList();

    }


    @Override
    public void updateFlightBooking(Integer flightBookingId, FlightBookingDTO flightBookingDTO) {

        //!TODO: hacer excepciones
        FlightBooking flightBooking = flightBookingRepository.findById(flightBookingId).get();


        if(flightBookingDTO.getSeatType() != null && !flightBookingDTO.getSeatType().isBlank()) flightBooking.setSeatType(flightBookingDTO.getSeatType());
        if(flightBookingDTO.getSeatPrice() != null && flightBookingDTO.getSeatPrice() > 0) flightBooking.setSeatPrice(flightBookingDTO.getSeatPrice());

        Flight flight = flightRepository.findFlightByIdAndNotDeleted(flightBooking.getFlight().getId()).get();

        List<FlightBooking> flightExistBookings = flight.getFlightBookingList().stream()
                .filter(booking -> !booking.isDeleted())
                .toList();

        boolean bookingExists = isBookingExists(flightExistBookings, flightBooking);

        if (!bookingExists) {

            throw new RuntimeException("Booking already exists");
        }

        flightBookingRepository.save(flightBooking);
    }


    @Override
    public void deleteFlightBooking(Integer flightBookingId) {

        //!TODO: hacer excepciones
            FlightBooking flightBooking = flightBookingRepository.findById(flightBookingId).get();

            flightBooking.setDeleted(true);

            flightBookingRepository.save(flightBooking);
    }



    @Override
    public List<FlightDTO> getFlightByDestinationOriginAndDate(String destination, String origin, LocalDate date) {

        return flightRepository.findByOriginAndDestinationAndDateAndNotDeleted(origin, destination, date).stream()
                .map(this::toGetFlightDTO)
                .toList();


    }

    @Override
    public void addFlightList(List<Flight> flightList) {

        flightList.forEach(flight -> flight.setFlightCode(flight.getOrigin(), flight.getDestination(),
                flightList.indexOf(flight) + 1));

        flightRepository.saveAll(flightList);
    }


    private FlightDTO toFlightDTO(Flight flight) {

        return new FlightDTO(flight.getFlightCode(), flight.getOrigin(), flight.getDestination(), flight.getDate(), flight.getAvailableSeats(), flight.getFlightBookingList().stream().map(this::toFlightBookingDTO).toList());
    }


    private FlightDTO toGetFlightDTO(Flight flight) {

        return new FlightDTO(flight.getFlightCode(), flight.getOrigin(), flight.getDestination(), flight.getDate(), flight.getAvailableSeats());
    }


    private FlightBookingDTO toFlightBookingDTO(FlightBooking flightBooking) {

        return new FlightBookingDTO(flightBooking.getBookingCode(), flightBooking.getFlight().getDate(), flightBooking.getFlight().getOrigin(), flightBooking.getFlight().getDestination(), flightBooking.getSeatType(), flightBooking.getSeatPrice(), flightBooking.getClientList().stream().map(this::toClientDTO).toList());
    }


    private ClientDTO toClientDTO(Client client){

        return new ClientDTO(client.getName(), client.getLastName(), client.getNif(), client.getEmail());
    }


}
