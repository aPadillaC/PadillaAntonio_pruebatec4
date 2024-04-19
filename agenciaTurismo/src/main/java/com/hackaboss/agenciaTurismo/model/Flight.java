package com.hackaboss.agenciaTurismo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String flightCode;
    private String origin;
    private String destination;
    private String seatType;
    private Double flightPrice;
    private LocalDate date;
    private boolean isDeleted;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "flightBooking",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> clientList;

    public Flight(Integer id, String flightCode, String origin, String destination, String seatType, Double flightPrice, LocalDate date, List<Client> clientList) {
        this.id = id;
        this.flightCode = flightCode;
        this.origin = origin;
        this.destination = destination;
        this.seatType = seatType;
        this.flightPrice = flightPrice;
        this.date = date;
        this.clientList = clientList;
        this.isDeleted = false;
    }
}
