package com.hackaboss.agenciaTurismo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
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

    @OneToMany(mappedBy = "client")
    private List<RoomBooking> roomBookingList;

    @ManyToMany(mappedBy = "clientList")
    private List<Flight> flightList;
}
