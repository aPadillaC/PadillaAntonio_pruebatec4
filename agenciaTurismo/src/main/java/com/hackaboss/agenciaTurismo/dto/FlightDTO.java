package com.hackaboss.agenciaTurismo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
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

    @NotNull(message = "Available seats must be informed")
    @Min(value = 0, message = "Available seats must be greater than 0")
    private Integer availableSeats;

    private List<FlightBookingDTO> flightBookingList;

    public FlightDTO(String flightCode, String origin, String destination, LocalDate date, Integer availableSeats) {
        this.flightCode = flightCode;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.availableSeats = availableSeats;
    }
}
