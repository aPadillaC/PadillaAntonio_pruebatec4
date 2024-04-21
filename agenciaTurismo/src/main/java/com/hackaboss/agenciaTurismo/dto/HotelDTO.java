package com.hackaboss.agenciaTurismo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {

    private Integer id;
    private String name;
    private String city;
    private String hotelCode;
    private List<RoomDTO> rooms;
}
