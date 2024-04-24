package com.hackaboss.agenciaTurismo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class RoomBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String bookingCode;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE,
            fallbackPatterns = {"yyy/MM/dd", "dd-MM-yy", "dd/MM/yyy"})
    private LocalDate dateFrom;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE,
            fallbackPatterns = {"yyy/MM/dd", "dd-MM-yy", "dd/MM/yyy"})
    private LocalDate dateTo;

    private boolean isCompleted;

    private boolean isDeleted;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Client client;


    public RoomBooking(Integer id, LocalDate dateFrom, LocalDate dateTo, Room room, Client client) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.room = room;
        this.client = client;
        this.isCompleted = false;
        this.isDeleted = false;
    }

    public void setBookingCode(String roomCode, Integer num) {
        this.bookingCode = roomCode + "/BR" + num;
    }

}
