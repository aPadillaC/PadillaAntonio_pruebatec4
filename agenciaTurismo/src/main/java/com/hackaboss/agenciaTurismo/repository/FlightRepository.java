package com.hackaboss.agenciaTurismo.repository;

import com.hackaboss.agenciaTurismo.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
