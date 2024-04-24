package com.hackaboss.agenciaTurismo.exception;

public class EntityNotFoundException extends RuntimeException{

        public EntityNotFoundException(String entity) {

            super("The " + entity + " was not found.");
        }
}
