package com.hackaboss.agenciaTurismo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hackaboss.agenciaTurismo.model.RoomBooking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDTO {

    private Integer id;
    private String roomType;
    private Double roomPrice;
    private String roomCode;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private List<RoomBookingDTO> roomBookingList;


    public RoomDTO(Integer id, String roomType, Double roomPrice, String roomCode, LocalDate dateFrom, LocalDate dateTo) {
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.roomCode = roomCode;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
