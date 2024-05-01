package com.hackaboss.agenciaTurismo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Size(min = 3, max = 40,
            message = "Room must be 3 characters long")
    @NotBlank(message = "Room type is required")
    private String roomType;

    @NotBlank(message = "Room price must be informed")
    @Min(value = 0, message = "Room price must be greater than 0")
    private Double roomPrice;

    private String roomCode;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE)
    @NotBlank(message = "Date from is required")
    private LocalDate dateFrom;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE)
    @NotBlank(message = "Date to is required")
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
