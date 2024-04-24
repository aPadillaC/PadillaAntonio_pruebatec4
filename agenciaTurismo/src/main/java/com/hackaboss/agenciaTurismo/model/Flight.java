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
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String flightCode;
    private String origin;
    private String destination;
    private LocalDate date;
    private Integer availableSeats;
    private boolean isDeleted;

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
    private List<FlightBooking> flightBookingList;


    public Flight(Integer id, String origin, String destination, LocalDate date, Integer availableSeats) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.availableSeats = availableSeats;
        this.flightBookingList = new ArrayList<>();
        this.isDeleted = false;
    }


    public void setFlightCode(String origin, String destination, Integer num) {

        this.flightCode = origin.substring(0,2) + destination.substring(0,3) + num;
    }


    public void upDateAvailableSeats(FlightBooking flightBooking) {

        this.availableSeats -=  flightBooking.getClientList().size();
    }


}
