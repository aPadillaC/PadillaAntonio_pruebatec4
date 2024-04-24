package com.hackaboss.agenciaTurismo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightDTO {

    private String flightCode;
    private String origin;
    private String destination;
    private LocalDate date;
    private Integer availableSeats;
    private List<FlightBookingDTO> flightBookingList;

    public FlightDTO(String flightCode, String origin, String destination, LocalDate date, Integer availableSeats) {
        this.flightCode = flightCode;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.availableSeats = availableSeats;
    }
}
