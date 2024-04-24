package com.hackaboss.agenciaTurismo.exception;

public class BookingNotFoundException extends RuntimeException{

    public BookingNotFoundException(String message) {

        super(message);
    }
}
