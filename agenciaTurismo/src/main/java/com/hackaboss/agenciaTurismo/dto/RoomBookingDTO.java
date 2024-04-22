package com.hackaboss.agenciaTurismo.dto;

import com.hackaboss.agenciaTurismo.model.Client;
import com.hackaboss.agenciaTurismo.model.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
public class RoomBookingDTO {

    private String bookingCode;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String city;
    private String hotelName;
    private String roomCode;
    private String roomType;
    private ClientDTO client;


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

    public RoomBookingDTO(LocalDate dateFrom, LocalDate dateTo, ClientDTO client) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.client = client;
    }


    public RoomBookingDTO(LocalDate dateFrom, LocalDate dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }


}
