package com.hackaboss.agenciaTurismo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {


    @NotBlank(message = "Name must be informed")
    private String name;


    @NotBlank(message = "Last name must be informed")
    private String lastName;

    @Pattern(regexp = "^\\d{8}[A-Za-z]$",
            message = "NIF must be 8 numbers followed by a letter")
    @NotBlank(message = "NIF must be informed")
    private String nif;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Email must be a valid email")
    @NotBlank(message = "Email must be informed")
    private String email;

}
