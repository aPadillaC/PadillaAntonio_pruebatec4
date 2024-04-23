package com.hackaboss.agenciaTurismo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {

    private String flightCode;
    private String origin;
    private String destination;
//    private String seatType;
//    private Double flightPrice;
    private LocalDate date;
    private Integer availableSeats;
    private List<FlightBookingDTO> flightBookingList;
}
