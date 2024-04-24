package com.hackaboss.agenciaTurismo.dto;

import com.hackaboss.agenciaTurismo.model.Client;
import com.hackaboss.agenciaTurismo.model.Room;
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
            iso = DateTimeFormat.ISO.DATE,
            fallbackPatterns = {"yyy/MM/dd", "dd-MM-yy", "dd/MM/yyy"})
    private LocalDate dateFrom;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE,
            fallbackPatterns = {"yyy/MM/dd", "dd-MM-yy", "dd/MM/yyy"})
    private LocalDate dateTo;

    @Size(min = 3, max = 40,
            message = "Origin must be 3 characters long")
    private String city;

    @Size(min = 3, max = 40,
            message = "Origin must be 3 characters long")
    private String hotelName;

    private String roomCode;

    @Size(min = 3, max = 40,
            message = "Origin must be 3 characters long")
    private String roomType;

    private ClientDTO client;


    // Constructor for get room-Booking
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

    // Constructor for add room-Booking
    public RoomBookingDTO(LocalDate dateFrom, LocalDate dateTo, ClientDTO client) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.client = client;
    }


    // Constructor for Update room-Booking
    public RoomBookingDTO(LocalDate dateFrom, LocalDate dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }


}
