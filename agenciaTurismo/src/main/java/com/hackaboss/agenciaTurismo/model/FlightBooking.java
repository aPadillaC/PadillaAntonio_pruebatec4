package com.hackaboss.agenciaTurismo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class FlightBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String bookingCode;

    @Size(min = 3, max = 40,
            message = "Origin must be 3 characters long")
    private String seatType;

    @NotNull(message = "Seat price must be informed")
    @Min(value = 0, message = "Seat price must be greater than 0")
    private Double seatPrice;

    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "client_flightBooking",
            joinColumns = @JoinColumn(name = "flightBooking_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private List<Client> clientList;

    public FlightBooking(Integer id,Flight flight, String seatType, Double seatPrice, List<Client> clientList) {
        this.id = id;
        this.flight = flight;
        this.seatType = seatType;
        this.seatPrice = seatPrice;
        this.clientList = clientList;
        this.isDeleted = false;
    }

    public void setBookingCode(String flightCode, Integer num) {

        this.bookingCode = flightCode + "/BF" + num;
    }

}
