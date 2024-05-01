package com.hackaboss.agenciaTurismo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Size(min = 3, max = 40,
            message = "Name must be 3 characters long")
    @NotBlank(message = "Name is required")
    private String name;

    @Size(min = 3, max = 40,
            message = "City must be 3 characters long")
    @NotBlank(message = "City is required")
    private String city;

    private boolean isDeleted;

    private String hotelCode;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    private List<Room> rooms;


    public Hotel(Integer id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.rooms = new ArrayList<>();
        this.isDeleted = false;
    }

    public void setHotelCode(String name, String city, Integer num) {

        this.hotelCode = name.toUpperCase().substring(0,2) + city.toUpperCase().substring(0,3) + num;
    }
}
