package com.hackaboss.agenciaTurismo.service;

import com.hackaboss.agenciaTurismo.dto.ClientDTO;
import com.hackaboss.agenciaTurismo.dto.FlightBookingDTO;
import com.hackaboss.agenciaTurismo.dto.FlightDTO;
import com.hackaboss.agenciaTurismo.exception.*;
import com.hackaboss.agenciaTurismo.model.*;
import com.hackaboss.agenciaTurismo.repository.FlightBookingRepository;
import com.hackaboss.agenciaTurismo.repository.FlightRepository;
import com.hackaboss.agenciaTurismo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class FlightService implements IFlightService{

    private  FlightRepository flightRepository;

    private  ClientRepository clientRepository;

    private  FlightBookingRepository flightBookingRepository;



    
    public FlightService(FlightRepository flightRepository, ClientRepository clientRepository,
                         FlightBookingRepository flightBookingRepository) {

        this.flightRepository = flightRepository;
        this.clientRepository = clientRepository;
        this.flightBookingRepository = flightBookingRepository;
    }





    @Override
    public void addFlight(Flight flight) {

        List<Flight> existingFlight = flightRepository.findSimilarFlight(flight.getOrigin(), flight.getDestination(),
                flight.getDate());

        if (!existingFlight.isEmpty()) {

            throw new FlightAlreadyExistsException("A flight with the same characteristics already exists.");

        }

        flight.setFlightCode(flight.getOrigin(), flight.getDestination(),  1);

        flightRepository.save(flight);
    }


    @Override
    public List<FlightDTO> getFlights() {

        List<Flight> flightList = flightRepository.findAllNotDeleted();

        if(flightList.isEmpty()) throw new FlightNotFoundException();

        return flightList.stream()
                .map(this::toGetFlightDTO)
                .toList();
    }


    @Override
    public FlightDTO getFlightById(Integer flightId) {

        return flightRepository.findFlightByIdAndNotDeleted(flightId)
                .map(this::toFlightDTO)
                .orElseThrow(FlightNotFoundException::new);
    }




    @Override
    public void updateFlight(Integer flightId, Flight flight) {

        Flight existingFlight = flightRepository.findFlightByIdAndNotDeleted(flightId)
                .orElseThrow(FlightNotFoundException::new);

        if(flight.getOrigin() != null && !flight.getOrigin().isBlank()) existingFlight.setOrigin(flight.getOrigin());
        if(flight.getDestination() != null && !flight.getDestination().isBlank()) existingFlight.setDestination(flight.getDestination());
        if(flight.getDate() != null) existingFlight.setDate(flight.getDate());
        if(flight.getAvailableSeats() != null) existingFlight.setAvailableSeats(flight.getAvailableSeats());

        existingFlight.setFlightCode(existingFlight.getOrigin(), existingFlight.getDestination(), existingFlight.getId());

        flightRepository.save(existingFlight);

    }



    @Override
    public void deleteFlight(Integer flightId) {

        Flight flight = flightRepository.findFlightByIdAndNotDeleted(flightId)
                .orElseThrow(FlightNotFoundException::new);

        boolean bookingExists = flight.getFlightBookingList().stream()
                .anyMatch(booking -> !booking.isDeleted());

        if (bookingExists) {
            throw new HasBookingsException("Flight cannot be deleted because it has bookings.");
        }

        flight.setDeleted(true);

        flightRepository.save(flight);
    }



    @Override
    public Double addFlightBooking(Integer flightId, FlightBookingDTO flightBookingDTO) {

        List<Client> clientList = getClientsOfBooking(flightBookingDTO);

        Flight flightExist = flightRepository.findFlightByIdAndNotDeleted(flightId)
                .orElseThrow(FlightNotFoundException::new);


        if (flightExist.getAvailableSeats() == 0) {

            throw new AllBookedException("No available seats");
        }

        FlightBooking flightBooking = new FlightBooking();

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
                throw new BookingAlreadyExistsException("Booking already exists");
            }
        }

        flightBookingRepository.save(flightBooking);

        flightExist.getFlightBookingList().add(flightBooking);
        flightExist.upDateAvailableSeats(flightBooking, "add");

        flightRepository.save(flightExist);

        return flightBooking.getSeatPrice() * flightBooking.getClientList().size();

    }




    @Override
    public List<FlightBookingDTO> getFlightBookingForFlightById(Integer flightId) {

        Flight flight = flightRepository.findFlightByIdAndNotDeleted(flightId)
                .orElseThrow(FlightNotFoundException::new);

        return flight.getFlightBookingList().stream()
                .filter(booking -> !booking.isDeleted())
                .map(this::toFlightBookingDTO)
                .toList();

    }



    @Override
    public void updateFlightBooking(Integer flightBookingId, FlightBookingDTO flightBookingDTO) {

        FlightBooking flightBooking = flightBookingRepository.findByIdAndNotDeleted(flightBookingId)
                .orElseThrow( () -> new BookingNotFoundException("Flight booking not found"));

        if(flightBookingDTO.getSeatType() != null && !flightBookingDTO.getSeatType().isBlank())
            flightBooking.setSeatType(flightBookingDTO.getSeatType());
        if(flightBookingDTO.getSeatPrice() != null && flightBookingDTO.getSeatPrice() > 0)
            flightBooking.setSeatPrice(flightBookingDTO.getSeatPrice());

        Flight flight = flightRepository.findFlightByIdAndNotDeleted(flightBooking.getFlight().getId())
                .orElseThrow(FlightNotFoundException::new);

        List<FlightBooking> flightExistBookings = flight.getFlightBookingList().stream()
                .filter(booking -> !booking.isDeleted())
                .toList();

        boolean bookingExists = isBookingExists(flightExistBookings, flightBooking);

        if (!bookingExists) {

            throw new BookingAlreadyExistsException("Booking already exists");
        }

        flightBookingRepository.save(flightBooking);
    }


    @Override
    public void deleteFlightBooking(Integer flightBookingId) {

            FlightBooking flightBooking = flightBookingRepository.findByIdAndNotDeleted(flightBookingId)
                    .orElseThrow( () -> new BookingNotFoundException("Flight booking not found"));

            flightBooking.setDeleted(true);
            flightBooking.getFlight().upDateAvailableSeats(flightBooking, "remove");

            flightRepository.save(flightBooking.getFlight());
            flightBookingRepository.save(flightBooking);
    }



    @Override
    public List<FlightDTO> getFlightByDestinationOriginAndDate(String destination, String origin, LocalDate dateTo,
                                                               LocalDate dateFrom) {

        List<Flight> flightList = flightRepository.findByOriginAndDestinationAndDates(origin, destination, dateTo, dateFrom);

        if(flightList.isEmpty()) throw new FlightNotFoundException();

        return flightList.stream()
                .map(this::toGetFlightDTO)
                .toList();


    }

    @Override
    public void addFlightList(List<Flight> flightList) {

        flightList.forEach(flight -> flight.setFlightCode(flight.getOrigin(), flight.getDestination(),
                flightList.indexOf(flight) + 1));

        flightRepository.saveAll(flightList);
    }



    /*
      Private methods
     */


    private List<Client> getClientsOfBooking(FlightBookingDTO flightBookingDTO) {

        List<Client> clientList = new ArrayList<>();

        List<ClientDTO> clientDTOList = flightBookingDTO.getClientList();

        clientDTOList.forEach(clientDTO -> {

            //Filter by dni and that is not deleted

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

            else if(existingClient.getName().equals(clientDTO.getName()) && existingClient.getLastName().
                    equals(clientDTO.getLastName())){

                clientList.add(existingClient);
            }

            else throw new ParameterConflictException("There is customer data that exists in the database but is wrong " +
                        "in the request.");
        });
        return clientList;
    }



    private boolean isBookingExists(List<FlightBooking> flightExistBooking, FlightBooking flightBooking) {

        Set<Client> flightBookingClientList = new HashSet<>(flightBooking.getClientList());

        return flightExistBooking.stream()
                .anyMatch(booking -> booking.getSeatType().equals(flightBooking.getSeatType()) &&
                        booking.getSeatPrice().equals(flightBooking.getSeatPrice()) &&
                        booking.getClientList().size() == flightBookingClientList.size() &&
                        new HashSet<>(booking.getClientList()).containsAll(flightBookingClientList) &&
                        booking.getFlight().equals(flightBooking.getFlight()));


    }



    /*
      DTOs methods are not private by necessity related to tests
     */

    /**
     * Method to convert a Flight object to a FlightDTO object
     */
    FlightDTO toFlightDTO(Flight flight) {

        return new FlightDTO(flight.getId(), flight.getFlightCode(), flight.getOrigin(), flight.getDestination(), flight.getDate(),
                flight.getAvailableSeats(), flight.getFlightBookingList().stream().map(this::toFlightBookingDTO).toList());
    }


    /**
     * Method to convert a Flight object to a FlightDTO object
     */
    FlightDTO toGetFlightDTO(Flight flight) {

        return new FlightDTO(flight.getId(), flight.getFlightCode(), flight.getOrigin(), flight.getDestination(), flight.getDate(),
                flight.getAvailableSeats());
    }


    /**
     * Method to convert a FlightBooking object to a FlightBookingDTO object
     */
    FlightBookingDTO toFlightBookingDTO(FlightBooking flightBooking) {

        return new FlightBookingDTO(flightBooking.getId(), flightBooking.getBookingCode(), flightBooking.getFlight().getDate(),
                flightBooking.getFlight().getOrigin(), flightBooking.getFlight().getDestination(), flightBooking.getSeatType(),
                flightBooking.getSeatPrice(), flightBooking.getClientList().stream().map(this::toClientDTO).toList());
    }


    /**
     * Method to convert a Client object to a ClientDTO object
     */
    ClientDTO toClientDTO(Client client){

        return new ClientDTO(client.getName(), client.getLastName(), client.getNif(), client.getEmail());
    }


}
