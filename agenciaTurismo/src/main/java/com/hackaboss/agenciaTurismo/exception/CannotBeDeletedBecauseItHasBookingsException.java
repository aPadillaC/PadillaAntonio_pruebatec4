package com.hackaboss.agenciaTurismo.exception;

public class CannotBeDeletedBecauseItHasBookingsException extends RuntimeException{

        public CannotBeDeletedBecauseItHasBookingsException(String message) {
            super(message);
        }

}
