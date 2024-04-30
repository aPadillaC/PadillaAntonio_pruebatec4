package com.hackaboss.agenciaTurismo.controller;

import com.hackaboss.agenciaTurismo.exception.*;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {


    private static final String TIMESTAMP = "timestamp";
    private static final String STATUS = "status";
    private static final String ERROR = "error";



    /**
     * Handle validation exceptions
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException e) {
        Map<String, Object> response = new HashMap<>();
        List<String> errors = e.getAllErrors()
                .stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .toList();

        response.put("errors", errors);
        response.put(TIMESTAMP, LocalDateTime.now());
        response.put(STATUS, HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }



    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException e) {

        Map<String, Object> response = new HashMap<>();
        List<String> errors = e.getAllErrors()
                .stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .toList();

        response.put("errors", errors);
        response.put(TIMESTAMP, LocalDateTime.now());
        response.put(STATUS, HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }




    @ExceptionHandler(ParameterConflictException.class)
    public ResponseEntity<Object> notValidParamException(RuntimeException ex) {

        Map<String, Object> response = new HashMap<>();

        response.put(ERROR, ex.getMessage());
        response.put(TIMESTAMP, LocalDateTime.now());
        response.put(STATUS, HttpStatus.NOT_ACCEPTABLE.value());

        // Handle the exception and return an appropriate response to the client.
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
    }



    @ExceptionHandler({BookingNotFoundException.class, FlightNotFoundException.class, EntityNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(RuntimeException ex) {

        Map<String, Object> response = new HashMap<>();

        response.put(ERROR, ex.getMessage());
        response.put(TIMESTAMP, LocalDateTime.now());
        response.put(STATUS, HttpStatus.NOT_FOUND.value());

        // Handle the exception and return an appropriate response to the client.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }



    @ExceptionHandler({BookingAlreadyExistsException.class,
            HasBookingsException.class, FlightAlreadyExistsException.class, AlreadyExistEntityException.class,
            AllBookedException.class})
    public ResponseEntity<Object> handleExistingEntitiesException(RuntimeException ex) {

        Map<String, Object> response = new HashMap<>();

        response.put(ERROR, ex.getMessage());
        response.put(TIMESTAMP, LocalDateTime.now());
        response.put(STATUS, HttpStatus.CONFLICT.value());

        // Handle the exception and return an appropriate response to the client.
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

}
