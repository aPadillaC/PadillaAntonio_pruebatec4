package com.hackaboss.agenciaTurismo.service;

import com.hackaboss.agenciaTurismo.dto.FlightDTO;
import com.hackaboss.agenciaTurismo.model.Flight;

import java.util.List;

public interface IFlightService {

    void addFlight(Flight flight);

    List<FlightDTO> getFlights();

    FlightDTO getFlightById(Integer flightId);

    void updateFlight(Integer flightId, Flight flight);

    void deleteFlight(Integer flightId);
}
