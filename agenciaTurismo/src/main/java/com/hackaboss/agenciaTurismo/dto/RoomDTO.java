package com.hackaboss.agenciaTurismo.dto;

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
public class RoomDTO {

    private Integer id;
    private String roomType;
    private Double roomPrice;
    private String roomCode;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private List<RoomBookingDTO> roomBookingList;
}
