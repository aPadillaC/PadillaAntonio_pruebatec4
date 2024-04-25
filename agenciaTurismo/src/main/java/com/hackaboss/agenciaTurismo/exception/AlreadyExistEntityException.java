package com.hackaboss.agenciaTurismo.exception;

public class AlreadyExistEntityException extends RuntimeException{

    public AlreadyExistEntityException(String entity) {

        super("A " + entity + " with the same/partial characteristics already exists.");
    }
}
