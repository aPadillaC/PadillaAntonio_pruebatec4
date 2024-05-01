package com.hackaboss.agenciaTurismo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Size(min = 3, max = 40,
            message = "Name must be 3 characters long")
    @NotBlank(message = "Name must be informed")
    private String name;

    @Size(min = 3, max = 40,
            message = "City must be 3 characters long")
    @NotBlank(message = "City must be informed")
    private String city;

    private String hotelCode;

    @Valid
    private List<RoomDTO> rooms;

    /**
     * Constructor for get one hotel
     */
    public HotelDTO(Integer id, String name, String city, String hotelCode) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.hotelCode = hotelCode;
    }
}
