package com.hackaboss.agenciaTurismo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private String lastName;
    private String nif;
    private String email;
    private boolean isDeleted;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<RoomBooking> roomBookingList;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<FlightBooking> flightBookingList;

    public Client(Integer id, String name, String lastName, String nif, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.nif = nif;
        this.email = email;
        this.roomBookingList = new ArrayList<>();
        this.flightBookingList = new ArrayList<>();
        this.isDeleted = false;
    }
}
