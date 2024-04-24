package com.hackaboss.agenciaTurismo.repository;

import com.hackaboss.agenciaTurismo.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    @Query("SELECT f FROM Flight f WHERE f.origin = :origin AND f.destination = :destination AND f.date = :date AND f.isDeleted = false")
    List<Flight> findByOriginAndDestinationAndDateAndNotDeleted(String origin, String destination, LocalDate date);


    @Query("SELECT f FROM Flight f WHERE f.isDeleted = false")
    List<Flight> findAllNotDeleted();


    @Query("SELECT f FROM Flight f WHERE f.id = :id AND f.isDeleted = false")
    Optional<Flight> findFlightByIdAndNotDeleted(Integer id);

}
