package com.hackaboss.agenciaTurismo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @Size(min = 2, max = 40,
            message = "Origin must be 3 characters long")
    private String name;

    @Size(min = 2, max = 40,
            message = "Origin must be 3 characters long")
    private String lastName;

    @Pattern(regexp = "^\\d{8}[A-Za-z]$",
            message = "NIF must be 8 numbers followed by a letter")
    private String nif;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Email must be a valid email")
    private String email;

    private boolean isDeleted;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<RoomBooking> roomBookingList;

    @ManyToMany(mappedBy = "clientList", fetch = FetchType.LAZY)
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
