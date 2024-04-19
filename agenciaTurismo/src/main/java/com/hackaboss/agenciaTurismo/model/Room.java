package com.hackaboss.agenciaTurismo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String roomType;
    private Double roomPrice;
    private boolean isBooked;
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    public Room(Integer id, String roomType, Double roomPrice, Hotel hotel) {
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.hotel = hotel;
        this.isBooked = false;
        this.isDeleted = false;
    }
}
