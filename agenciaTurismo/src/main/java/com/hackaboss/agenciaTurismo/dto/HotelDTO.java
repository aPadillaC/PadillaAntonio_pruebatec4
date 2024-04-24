package com.hackaboss.agenciaTurismo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelDTO {

    private Integer id;
    private String name;
    private String city;
    private String hotelCode;
    private List<RoomDTO> rooms;

    public HotelDTO(Integer id, String name, String city, String hotelCode) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.hotelCode = hotelCode;
    }
}
