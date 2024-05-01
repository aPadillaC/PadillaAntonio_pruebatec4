package com.hackaboss.agenciaTurismo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDTO {

    private Integer id;

    @Size(min = 3, max = 40,
            message = "Origin must be 3 characters long")
    @NotBlank(message = "Room type must be informed")
    private String roomType;

    @Min(value = 0, message = "Room price must be greater than 0")
    @NotBlank(message = "Room price must be informed")
    private Double roomPrice;

    private String roomCode;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE)
    @NotBlank(message = "Date from must be informed")
    private LocalDate dateFrom;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE)
    @NotBlank(message = "Date to must be informed")
    private LocalDate dateTo;

    @Valid
    private List<RoomBookingDTO> roomBookingList;


    public RoomDTO(Integer id, String roomType, Double roomPrice, String roomCode, LocalDate dateFrom, LocalDate dateTo) {
        this.id = id;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.roomCode = roomCode;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
