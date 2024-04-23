package com.hackaboss.agenciaTurismo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightBookingDTO {

    private String bookingCode;
    private Integer peopleQ;
    private String seatType;
    private Double seatPrice;
    private List<ClientDTO> clientList;

    public FlightBookingDTO(String bookingCode, String seatType, Double seatPrice, List<ClientDTO> clientList) {
        this.bookingCode = bookingCode;
        this.peopleQ = clientList.size();
        this.seatType = seatType;
        this.seatPrice = seatPrice;
        this.clientList = clientList;
    }
}
