package com.hackaboss.agenciaTurismo.model;

import jakarta.persistence.*;
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

    private String name;
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

        this.hotelCode = name.substring(0,2) + city.substring(0,3) + num;
    }
}
