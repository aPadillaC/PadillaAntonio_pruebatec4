package com.hackaboss.agenciaTurismo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightBookingDTO {

    private String bookingCode;
    private LocalDate date;
    private String origin;
    private String destination;
    private Integer peopleQ;
    private String seatType;
    private Double seatPrice;
    private List<ClientDTO> clientList;

    // Constructor for get flight-Booking
    public FlightBookingDTO(String bookingCode, LocalDate date, String origin, String destination, String seatType, Double seatPrice, List<ClientDTO> clientList) {
        this.bookingCode = bookingCode;
        this.date = date;
        this.origin = origin;
        this.destination = destination;
        this.peopleQ = clientList.size();
        this.seatType = seatType;
        this.seatPrice = seatPrice;
        this.clientList = clientList;
    }


    // Constructor for add flight-Booking
    public FlightBookingDTO(String seatType, Double seatPrice, List<ClientDTO> clientList) {
        this.seatType = seatType;
        this.seatPrice = seatPrice;
        this.clientList = clientList;
    }


    // Constructor for Update flight-Booking
    public FlightBookingDTO(String seatType, Double seatPrice) {
        this.seatType = seatType;
        this.seatPrice = seatPrice;
    }


}
