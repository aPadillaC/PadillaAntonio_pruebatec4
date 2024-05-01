package com.hackaboss.agenciaTurismo.controller;

import com.hackaboss.agenciaTurismo.dto.FlightDTO;
import com.hackaboss.agenciaTurismo.exception.FlightNotFoundException;
import com.hackaboss.agenciaTurismo.model.Flight;
import com.hackaboss.agenciaTurismo.service.IFlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FlightControllerTest {

    @InjectMocks
    private FightController fightController;

    @Mock
    private IFlightService flightService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddFlight() {
        // given
        Flight flight = new Flight();
        flight.setFlightCode("FC123");
        flight.setOrigin("Madrid");
        flight.setDestination("Barcelona");
        flight.setDate(LocalDate.of(2023, 1, 1));
        flight.setAvailableSeats(100);

        // when
        fightController.addFlight(flight);

        // then
        verify(flightService).addFlight(flight);
    }



    @Test
    void testGetFlights() {
        // given
        Flight flight = new Flight();
        flight.setId(1);
        flight.setFlightCode("FC123");
        flight.setOrigin("Madrid");
        flight.setDestination("Barcelona");
        flight.setDate(LocalDate.of(2023, 1, 1));
        flight.setAvailableSeats(100);

        FlightDTO flightDTO = new FlightDTO(flight.getId(), flight.getFlightCode(), flight.getOrigin(), flight.getDestination(), flight.getDate(), flight.getAvailableSeats());

        when(flightService.getFlights()).thenReturn(Arrays.asList(flightDTO));

        // when
        ResponseEntity<List<FlightDTO>> response = fightController.getFlights();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(flight.getFlightCode(), response.getBody().get(0).getFlightCode());
        assertEquals(flight.getOrigin(), response.getBody().get(0).getOrigin());
        assertEquals(flight.getDestination(), response.getBody().get(0).getDestination());
        assertEquals(flight.getDate(), response.getBody().get(0).getDate());
        assertEquals(flight.getAvailableSeats(), response.getBody().get(0).getAvailableSeats());
    }



    @Test
    void testGetFlightById() {
        // given
        Flight flight = new Flight();
        flight.setId(1);
        flight.setFlightCode("FC123");
        flight.setOrigin("Madrid");
        flight.setDestination("Barcelona");
        flight.setDate(LocalDate.of(2023, 1, 1));
        flight.setAvailableSeats(100);

        FlightDTO flightDTO = new FlightDTO(flight.getId(), flight.getFlightCode(), flight.getOrigin(), flight.getDestination(), flight.getDate(), flight.getAvailableSeats());

        when(flightService.getFlightById(1)).thenReturn(flightDTO);

        // when
        ResponseEntity<FlightDTO> response = fightController.getFlightById(1);

        // then
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(flight.getFlightCode(), response.getBody().getFlightCode());
        assertEquals(flight.getOrigin(), response.getBody().getOrigin());
        assertEquals(flight.getDestination(), response.getBody().getDestination());
        assertEquals(flight.getDate(), response.getBody().getDate());
        assertEquals(flight.getAvailableSeats(), response.getBody().getAvailableSeats());
    }



    @Test
    void testGetFlightByIdNotFound() {
        // given
        when(flightService.getFlightById(1)).thenThrow(FlightNotFoundException.class);

        // then
        assertThrows(FlightNotFoundException.class, () -> fightController.getFlightById(1));
    }



    @Test
    void testGetFlightByIdInvalidId() {

        // given
        when(flightService.getFlightById(-1)).thenThrow(HandlerMethodValidationException.class);

        // then
        assertThrows(HandlerMethodValidationException.class, () -> fightController.getFlightById(-1));
    }
}
