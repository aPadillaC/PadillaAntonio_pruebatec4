package com.hackaboss.agenciaTurismo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String user;
    private String password;
    private String token;

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }
}
