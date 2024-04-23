package com.hackaboss.agenciaTurismo.service;

import com.hackaboss.agenciaTurismo.dto.ClientDTO;
import com.hackaboss.agenciaTurismo.dto.FlightBookingDTO;
import com.hackaboss.agenciaTurismo.dto.FlightDTO;
import com.hackaboss.agenciaTurismo.exception.FlightAlreadyExistsException;
import com.hackaboss.agenciaTurismo.model.Flight;
import com.hackaboss.agenciaTurismo.model.Client;
import com.hackaboss.agenciaTurismo.model.FlightBooking;
import com.hackaboss.agenciaTurismo.model.Hotel;
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
        List<Flight> existingFlight = flightRepository.findByOriginAndDestinationAndDateAndNotDeleted(flight.getOrigin(), flight.getDestination(), flight.getDate()).get();

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
        return flightRepository.findByIdAndNotDeleted(flightId)
                .map(this::toFlightDTO)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
    }



    //!TODO: hacer excepcion
    @Override
    public void updateFlight(Integer flightId, Flight flight) {

        Flight existingFlight = flightRepository.findByIdAndNotDeleted(flightId).get();

        if(flight.getOrigin() != null && !flight.getOrigin().isBlank()) existingFlight.setOrigin(flight.getOrigin());
        if(flight.getDestination() != null && !flight.getDestination().isBlank()) existingFlight.setDestination(flight.getDestination());
        if(flight.getDate() != null) existingFlight.setDate(flight.getDate());
        if(flight.getAvailableSeats() != null) existingFlight.setAvailableSeats(flight.getAvailableSeats());

        existingFlight.setFlightCode(existingFlight.getOrigin(), existingFlight.getDestination(), existingFlight.getId());

        flightRepository.save(existingFlight);

    }



    @Override
    public void deleteFlight(Integer flightId) {

        Flight flight = flightRepository.findByIdAndNotDeleted(flightId).get();

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
        Flight flightExist = flightRepository.findByIdAndNotDeleted(flightId).get();

        FlightBooking flightBooking = new FlightBooking();

//        if (flightExist == null) {
//
//            throw new RuntimeException("Flight not found");
//        }
//        else{

            if (flightExist.getAvailableSeats() == 0) {

                throw new RuntimeException("No available seats");
            }


            flightBooking.setFlight(flightExist);
            flightBooking.setBookingCode(flightExist.getFlightCode(), flightExist.getFlightBookingList().size() + 1);
            flightBooking.setSeatType(flightBookingDTO.getSeatType());
            flightBooking.setSeatPrice(flightBookingDTO.getSeatPrice());
            flightBooking.setClientList(clientList);
//
//        }


        flightBookingRepository.save(flightBooking);

        flightExist.getFlightBookingList().add(flightBooking);

        flightRepository.save(flightExist);

        return flightBooking.getSeatPrice() * flightBooking.getClientList().size();

    }


    private FlightDTO toFlightDTO(Flight flight) {

        return new FlightDTO(flight.getFlightCode(), flight.getOrigin(), flight.getDestination(), flight.getDate(), flight.getAvailableSeats(), flight.getFlightBookingList().stream().map(this::toFlightBookingDTO).toList());
    }


    private FlightBookingDTO toFlightBookingDTO(FlightBooking flightBooking) {

        return new FlightBookingDTO(flightBooking.getBookingCode(), flightBooking.getSeatType(), flightBooking.getSeatPrice(), flightBooking.getClientList().stream().map(this::toClientDTO).toList());
    }


    private ClientDTO toClientDTO(Client client){

        return new ClientDTO(client.getName(), client.getLastName(), client.getNif(), client.getEmail());
    }

    /**
    public void createFlightAndAddClients() {
        // Create a new flight
        Flight flight = new Flight();
        flight.setFlightCode("FL1");
        flight.setOrigin("City1");
        flight.setDestination("City2");
        flight.setSeatType("Economy");
        flight.setFlightPrice(100.0);
        flight.setDate(LocalDate.now());
        flight.setAvailableSeats(5);
        flight.setClientList(new ArrayList<>());


        // Save the flight
        flight = flightRepository.save(flight);

        //Create and add 5 clients to the flight
        for (int i = 1; i <= 5; i++) {
            Client client = new Client();

            client.setName("Client" + i);
            client.setLastName("LastName" + i);
            client.setNif("NIF" + i);
            client.setEmail("client" + i + "@example.com");


             //Save the client
            client = clientRepository.save(client);

             //Add the client to the flight
            flight.addClient(client);
        }

        // Update the flight
        flightRepository.save(flight);
    }

    public void deleteFlight() {

        Flight flight = flightRepository.findById(102).get();

        List<Client> flightClients = flight.getClientList();
        List<Client> clientsToRemove = new ArrayList<>();

        for (Client client : flightClients) {
            if (client.getId() % 2 == 0) {
                clientsToRemove.add(client);
            }
        }

        for (Client client : clientsToRemove) {
            flight.removeClient(client);
        }

        flightRepository.save(flight);
    }
     */
}
