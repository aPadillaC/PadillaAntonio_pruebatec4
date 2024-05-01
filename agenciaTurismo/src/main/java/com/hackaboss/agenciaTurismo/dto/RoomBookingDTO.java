package com.hackaboss.agenciaTurismo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
public class RoomBookingDTO {

    private String bookingCode;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "DateFrom is required")
    private LocalDate dateFrom;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "DateTo is required")
    private LocalDate dateTo;

    private String city;

    private String hotelName;

    private String roomCode;

    private String roomType;

    @Valid
    private ClientDTO client;


    /**
     * Constructor for get room-Booking
     */
    public RoomBookingDTO(String bookingCode, LocalDate dateFrom, LocalDate dateTo, String city, String hotelName, String roomCode, String roomType, ClientDTO client) {
        this.bookingCode = bookingCode;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.city = city;
        this.hotelName = hotelName;
        this.roomCode = roomCode;
        this.roomType = roomType;
        this.client = client;
    }



    /**
     * Constructor for add room-Booking
     */
    public RoomBookingDTO(LocalDate dateFrom, LocalDate dateTo, ClientDTO client) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.client = client;
    }



    /**
     * Constructor for update room-Booking
     */
    public RoomBookingDTO(LocalDate dateFrom, LocalDate dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }


}
