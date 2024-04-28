package com.hackaboss.agenciaTurismo.service;

import com.hackaboss.agenciaTurismo.dto.ClientDTO;
import com.hackaboss.agenciaTurismo.dto.FlightBookingDTO;
import com.hackaboss.agenciaTurismo.dto.FlightDTO;
import com.hackaboss.agenciaTurismo.exception.AllBookedException;
import com.hackaboss.agenciaTurismo.exception.FlightNotFoundException;
import com.hackaboss.agenciaTurismo.model.Client;
import com.hackaboss.agenciaTurismo.model.Flight;
import com.hackaboss.agenciaTurismo.model.FlightBooking;
import com.hackaboss.agenciaTurismo.repository.ClientRepository;
import com.hackaboss.agenciaTurismo.repository.FlightBookingRepository;
import com.hackaboss.agenciaTurismo.repository.FlightRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    @InjectMocks
    private FlightService flightServiceInyectado;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private FlightBookingRepository flightBookingRepository;

    @Mock
    private ClientRepository clientRepository;


    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void testGetFlights() {

        Flight flight1 = new Flight();
        flight1.setFlightCode("FC1");
        flight1.setOrigin("Origin Test 1");
        flight1.setDestination("Destination Test 1");
        flight1.setDate(LocalDate.of(2023, 1, 1));
        flight1.setAvailableSeats(100);

        Flight flight2 = new Flight();
        flight2.setFlightCode("FC2");
        flight2.setOrigin("Origin Test 2");
        flight2.setDestination("Destination Test 2");
        flight2.setDate(LocalDate.of(2023, 1, 2));
        flight2.setAvailableSeats(200);

        List<Flight> flights = Arrays.asList(flight1, flight2);

        when(flightRepository.findAllNotDeleted()).thenReturn(flights);

        List<FlightDTO> flightDTOs = flightServiceInyectado.getFlights();

        assertEquals(flights.size(), flightDTOs.size());
        for (int i = 0; i < flights.size(); i++) {
            assertEquals(flights.get(i).getFlightCode(), flightDTOs.get(i).getFlightCode());
            assertEquals(flights.get(i).getOrigin(), flightDTOs.get(i).getOrigin());
            assertEquals(flights.get(i).getDestination(), flightDTOs.get(i).getDestination());
            assertEquals(flights.get(i).getDate(), flightDTOs.get(i).getDate());
            assertEquals(flights.get(i).getAvailableSeats(), flightDTOs.get(i).getAvailableSeats());
        }
    }



    @Test
    public void testGetFlightsEx() {

        List<Flight> flights = new ArrayList<>();

       assertThrows(FlightNotFoundException.class, () -> {
            flightServiceInyectado.getFlights();
        });
    }



    @Test
    public void testGetFlightByDestinationOriginAndDate() {

        String origin = "Origin Test";
        String destination = "Destination Test";
        LocalDate date = LocalDate.of(2023, 1, 1);

        Flight flight1 = new Flight();
        flight1.setFlightCode("FC1");
        flight1.setOrigin(origin);
        flight1.setDestination(destination);
        flight1.setDate(date);
        flight1.setAvailableSeats(100);

        Flight flight2 = new Flight();
        flight2.setFlightCode("FC2");
        flight2.setOrigin(origin);
        flight2.setDestination(destination);
        flight2.setDate(date);
        flight2.setAvailableSeats(200);

        List<Flight> flights = Arrays.asList(flight1, flight2);


        when(flightRepository.findByOriginAndDestinationAndDateAndNotDeleted(origin, destination, date)).thenReturn(flights);

        List<FlightDTO> flightDTOs = flightServiceInyectado.getFlightByDestinationOriginAndDate(destination, origin, date);

        assertEquals(flights.size(), flightDTOs.size());
        for (int i = 0; i < flights.size(); i++) {
            assertEquals(flights.get(i).getFlightCode(), flightDTOs.get(i).getFlightCode());
            assertEquals(flights.get(i).getOrigin(), flightDTOs.get(i).getOrigin());
            assertEquals(flights.get(i).getDestination(), flightDTOs.get(i).getDestination());
            assertEquals(flights.get(i).getDate(), flightDTOs.get(i).getDate());
            assertEquals(flights.get(i).getAvailableSeats(), flightDTOs.get(i).getAvailableSeats());
        }
    }



    @Test
    public void testGetFlightByDestinationOriginAndDateEx() {

        String origin = "Origin Test";
        String destination = "Destination Test";
        LocalDate date = LocalDate.of(2023, 1, 1);

        List<Flight> flights = new ArrayList<>();


        when(flightRepository.findByOriginAndDestinationAndDateAndNotDeleted(origin, destination, date)).thenReturn(flights);

        assertThrows(FlightNotFoundException.class, () -> {
            flightServiceInyectado.getFlightByDestinationOriginAndDate(destination, origin, date);
        });
    }




    @Test
    public void testAddFlightBooking() {

        Integer flightId = 1;
        FlightBookingDTO flightBookingDTO = new FlightBookingDTO();
        flightBookingDTO.setSeatType("Economy");
        flightBookingDTO.setSeatPrice(100.0);
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Test");
        clientDTO.setLastName("User");
        clientDTO.setNif("12345678A");
        clientDTO.setEmail("test@test.com");
        flightBookingDTO.setClientList(Arrays.asList(clientDTO));

        Flight flight = new Flight();
        flight.setFlightCode("FC1");
        flight.setOrigin("Origin Test");
        flight.setDestination("Destination Test");
        flight.setDate(LocalDate.of(2023, 1, 1));
        flight.setAvailableSeats(100);
        flight.setFlightBookingList(new ArrayList<>());

        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setLastName(clientDTO.getLastName());
        client.setNif(clientDTO.getNif());
        client.setEmail(clientDTO.getEmail());

        when(flightRepository.findFlightByIdAndNotDeleted(flightId)).thenReturn(Optional.of(flight));
        when(clientRepository.findByNifAndNotDeleted(clientDTO.getNif())).thenReturn(client);

        Double result = flightServiceInyectado.addFlightBooking(flightId, flightBookingDTO);

        Double expected = flightBookingDTO.getSeatPrice() * flightBookingDTO.getClientList().size();
        assertEquals(expected, result);
    }



    @Test
    public void testAddFlightBookingEx() {

        Integer flightId = 1;
        FlightBookingDTO flightBookingDTO = new FlightBookingDTO();
        flightBookingDTO.setSeatType("Economy");
        flightBookingDTO.setSeatPrice(100.0);
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Test");
        clientDTO.setLastName("User");
        clientDTO.setNif("12345678A");
        clientDTO.setEmail("test@test.com");
        flightBookingDTO.setClientList(List.of(clientDTO));

        Flight flight = new Flight();
        flight.setId(1);
        flight.setFlightCode("FC1");
        flight.setOrigin("Origin Test");
        flight.setDestination("Destination Test");
        flight.setDate(LocalDate.of(2023, 1, 1));
        flight.setAvailableSeats(100);
        flight.setFlightBookingList(new ArrayList<>());
        flight.setAvailableSeats(0);

        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setLastName(clientDTO.getLastName());
        client.setNif(clientDTO.getNif());
        client.setEmail(clientDTO.getEmail());

        assertThrows(FlightNotFoundException.class, () -> {
            flightServiceInyectado.addFlightBooking(flightId, flightBookingDTO);
        });
    }



    @Test
    public void testToFlightDTO() {

        Flight flight = new Flight();
        flight.setFlightCode("FC1");
        flight.setOrigin("Origin Test");
        flight.setDestination("Destination Test");
        flight.setDate(LocalDate.of(2023, 1, 1));
        flight.setAvailableSeats(100);

        List<FlightBooking> flightBookingList = new ArrayList<>();
        flight.setFlightBookingList(flightBookingList);

        FlightDTO flightDTO = flightServiceInyectado.toFlightDTO(flight);

        assertEquals(flight.getFlightCode(), flightDTO.getFlightCode());
        assertEquals(flight.getOrigin(), flightDTO.getOrigin());
        assertEquals(flight.getDestination(), flightDTO.getDestination());
        assertEquals(flight.getDate(), flightDTO.getDate());
        assertEquals(flight.getAvailableSeats(), flightDTO.getAvailableSeats());
        assertEquals(flight.getFlightBookingList().size(), flightDTO.getFlightBookingList().size());
    }



    @Test
    public void testToGetFlightDTO() {

        Flight flight = new Flight();
        flight.setFlightCode("FC1");
        flight.setOrigin("Origin Test");
        flight.setDestination("Destination Test");
        flight.setDate(LocalDate.of(2023, 1, 1));
        flight.setAvailableSeats(100);

        FlightDTO flightDTO = flightServiceInyectado.toGetFlightDTO(flight);

        assertEquals(flight.getFlightCode(), flightDTO.getFlightCode());
        assertEquals(flight.getOrigin(), flightDTO.getOrigin());
        assertEquals(flight.getDestination(), flightDTO.getDestination());
        assertEquals(flight.getDate(), flightDTO.getDate());
        assertEquals(flight.getAvailableSeats(), flightDTO.getAvailableSeats());
    }



    @Test
    public void testToFlightBookingDTO() {
        // Crear una instancia de FlightBooking
        FlightBooking flightBooking = new FlightBooking();
        flightBooking.setBookingCode("FB1");
        flightBooking.setSeatType("Economy");
        flightBooking.setSeatPrice(100.0);

        List<Client> clientList = new ArrayList<>();
        flightBooking.setClientList(clientList);

        Flight flight = new Flight();
        flight.setFlightCode("FC1");
        flight.setOrigin("Origin Test");
        flight.setDestination("Destination Test");
        flight.setDate(LocalDate.of(2023, 1, 1));
        flight.setAvailableSeats(100);

        flightBooking.setFlight(flight);

        FlightBookingDTO flightBookingDTO = flightServiceInyectado.toFlightBookingDTO(flightBooking);

        assertEquals(flightBooking.getBookingCode(), flightBookingDTO.getBookingCode());
        assertEquals(flightBooking.getSeatType(), flightBookingDTO.getSeatType());
        assertEquals(flightBooking.getSeatPrice(), flightBookingDTO.getSeatPrice());
        assertEquals(flightBooking.getClientList().size(), flightBookingDTO.getClientList().size());
    }



    @Test
    public void testToClientDTO() {

        Client client = new Client();
        client.setName("Client Test");
        client.setLastName("Test Lastname");
        client.setNif("12345678A");
        client.setEmail("test@test.com");

        ClientDTO clientDTO = flightServiceInyectado.toClientDTO(client);

        assertEquals(client.getName(), clientDTO.getName());
        assertEquals(client.getLastName(), clientDTO.getLastName());
        assertEquals(client.getNif(), clientDTO.getNif());
        assertEquals(client.getEmail(), clientDTO.getEmail());
    }
}
