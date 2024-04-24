package com.hackaboss.agenciaTurismo.dto;

import jakarta.validation.constraints.Min;
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

    private String bookingCode;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE,
            fallbackPatterns = {"yyy/MM/dd", "dd-MM-yy", "dd/MM/yyy"})
    private LocalDate date;

    @Size(min = 3, max = 40,
            message = "Origin must be 3 characters long")
    private String origin;

    @Size(min = 3, max = 40,
            message = "Origin must be 3 characters long")
    private String destination;

    @Min(value = 0, message = "People quantity must be greater than 0")
    private Integer peopleQ;

    @Size(min = 3, max = 40,
            message = "Origin must be 3 characters long")
    private String seatType;

    @NotNull(message = "Seat price must be informed")
    @Min(value = 0, message = "Seat price must be greater than 0")
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
