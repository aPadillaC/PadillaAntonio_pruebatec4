package com.hackaboss.agenciaTurismo.repository;

import com.hackaboss.agenciaTurismo.model.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking, Integer> {
}
