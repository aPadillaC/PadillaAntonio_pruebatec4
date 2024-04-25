package com.hackaboss.agenciaTurismo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Size(min = 3, max = 40,
            message = "Origin must be 3 characters long")
    private String origin;

    @Size(min = 3, max = 40,
            message = "Origin must be 3 characters long")
    private String destination;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE,
            fallbackPatterns = {"yyy/MM/dd", "dd-MM-yy", "dd/MM/yyy"})
    private LocalDate date;

    @Min(value = 0, message = "Available seats must be greater than 0")
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

        this.flightCode = origin.toUpperCase().substring(0,2) + destination.toUpperCase().substring(0,3) + num;
    }


    public void upDateAvailableSeats(FlightBooking flightBooking, String action) {

        if(action.equals("add")) this.availableSeats -= flightBooking.getClientList().size();
        else this.availableSeats +=  flightBooking.getClientList().size();
    }


}
