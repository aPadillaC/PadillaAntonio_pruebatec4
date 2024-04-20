package com.hackaboss.agenciaTurismo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    private String hotelCode;
    private String name;
    private String city;
    private boolean isDeleted;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    private List<Room> rooms;


    public Hotel(Integer id, String hotelCode, String name, String city) {
        this.id = id;
        this.hotelCode = hotelCode;
        this.name = name;
        this.city = city;
        this.rooms = new ArrayList<>();
        this.isDeleted = false;
    }
}
