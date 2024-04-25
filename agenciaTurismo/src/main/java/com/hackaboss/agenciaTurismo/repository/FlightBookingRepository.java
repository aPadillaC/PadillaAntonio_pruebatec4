package com.hackaboss.agenciaTurismo.repository;

import com.hackaboss.agenciaTurismo.model.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking, Integer> {

    @Query("SELECT fb FROM FlightBooking fb WHERE fb.id = :id AND fb.isDeleted = false")
    Optional<FlightBooking> findByIdAndNotDeleted(Integer id);

}
