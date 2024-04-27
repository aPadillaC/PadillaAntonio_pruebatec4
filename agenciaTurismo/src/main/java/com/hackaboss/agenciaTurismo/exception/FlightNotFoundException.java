package com.hackaboss.agenciaTurismo.exception;

public class FlightNotFoundException extends RuntimeException {

    public FlightNotFoundException() {

        super("Flight/s not found");
    }
}
