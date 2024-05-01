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
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightDTO {

    private Integer flightId;

    private String flightCode;

    @Size(min = 3, max = 40,
            message = "Origin must be 3 characters long")
    @NotBlank(message = "Origin must be informed")
    private String origin;

    @Size(min = 3, max = 40,
            message = "Origin must be 3 characters long")
    @NotBlank(message = "Destination must be informed")
    private String destination;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE)
    @NotBlank(message = "Date must be informed")
    private LocalDate date;

    @NotNull(message = "Available seats must be informed")
    @Min(value = 0, message = "Available seats must be greater than 0")
    @NotBlank(message = "Available seats must be informed")
    private Integer availableSeats;

    @Valid
    private List<FlightBookingDTO> flightBookingList;



    /**
     * Constructor for get flight
     */
    public FlightDTO(Integer flightId, String flightCode, String origin, String destination, LocalDate date, Integer availableSeats) {
        this.flightId = flightId;
        this.flightCode = flightCode;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.availableSeats = availableSeats;
    }
}
