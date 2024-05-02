package com.hackaboss.agenciaTurismo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightBookingDTO {

    private Integer id;

    private String bookingCode;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    private String origin;

    private String destination;

    @Min(value = 0, message = "People quantity must be greater than 0")
    private Integer peopleQ;

    @Size(min = 3, max = 40,
            message = "Seat type must be 3 characters long")
    @NotBlank(message = "Seat type is required")
    private String seatType;

    @NotNull(message = "Seat price is required")
    @Min(value = 0, message = "Seat price must be greater than 0")
    private Double seatPrice;

    @Valid
    private List<ClientDTO> clientList;

    // Constructor for get flight-Booking
    public FlightBookingDTO(Integer id, String bookingCode, LocalDate date, String origin, String destination, String seatType, Double seatPrice, List<ClientDTO> clientList) {
        this.id = id;
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
