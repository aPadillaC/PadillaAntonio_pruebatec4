package com.hackaboss.agenciaTurismo.exception;

public class BookingAlreadyExistsException extends RuntimeException{

    public BookingAlreadyExistsException(String message) {

        super(message);
    }
}
