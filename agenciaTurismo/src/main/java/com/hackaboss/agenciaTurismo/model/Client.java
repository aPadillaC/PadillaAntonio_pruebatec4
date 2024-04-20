package com.hackaboss.agenciaTurismo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToMany(mappedBy = "client")
    private List<RoomBooking> roomBookingList;

    @ManyToMany(mappedBy = "clientList")
    private List<Flight> flightList;

    public Client(Integer id, String name, String lastName, String nif, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.nif = nif;
        this.email = email;
        this.isDeleted = false;
    }
}
