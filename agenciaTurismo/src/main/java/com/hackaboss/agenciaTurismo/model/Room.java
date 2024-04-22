package com.hackaboss.agenciaTurismo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private String roomCode;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private boolean isBooked;
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<RoomBooking> roomBookingList;


    public Room(Integer id, String roomType, Double roomPrice, LocalDate dateFrom, LocalDate dateTo){
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.roomBookingList = new ArrayList<>();
        this.isBooked = false;
        this.isDeleted = false;
    }


    public void setRoomCode(String hotelCode, Integer num) {
        this.roomCode = hotelCode + "/R" + num;
    }
}
