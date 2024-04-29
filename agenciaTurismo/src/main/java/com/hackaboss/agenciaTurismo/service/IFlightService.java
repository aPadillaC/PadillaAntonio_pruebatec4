package com.hackaboss.agenciaTurismo.service;

import com.hackaboss.agenciaTurismo.dto.FlightBookingDTO;
import com.hackaboss.agenciaTurismo.dto.FlightDTO;
import com.hackaboss.agenciaTurismo.model.Flight;

import java.time.LocalDate;
import java.util.List;

public interface IFlightService {

    void addFlight(Flight flight);

    List<FlightDTO> getFlights();

    FlightDTO getFlightById(Integer flightId);

    void updateFlight(Integer flightId, Flight flight);

    void deleteFlight(Integer flightId);

    Double addFlightBooking(Integer flightId, FlightBookingDTO flightBookingDTO);

    List<FlightBookingDTO> getFlightBookingForFlightById(Integer flightId);

    void updateFlightBooking(Integer flightBookingId, FlightBookingDTO flightBookingDTO);

    void deleteFlightBooking(Integer flightBookingId);

    List<FlightDTO> getFlightByDestinationOriginAndDate(String destination, String origin, LocalDate dateTo, LocalDate dateFrom);

    void addFlightList(List<Flight> flightList);
}
