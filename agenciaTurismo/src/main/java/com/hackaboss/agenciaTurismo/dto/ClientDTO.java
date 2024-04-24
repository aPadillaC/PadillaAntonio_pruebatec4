package com.hackaboss.agenciaTurismo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    @Size(min = 2, max = 40,
            message = "Origin must be 3 characters long")
    private String name;

    @Size(min = 2, max = 40,
            message = "Origin must be 3 characters long")
    private String lastName;

    @Pattern(regexp = "^\\d{8}[A-Za-z]$",
            message = "NIF must be 8 numbers followed by a letter")
    private String nif;

    @Email(message = "Email must be a valid email")
    private String email;

}
