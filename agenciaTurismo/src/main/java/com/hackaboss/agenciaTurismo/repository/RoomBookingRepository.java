package com.hackaboss.agenciaTurismo.repository;

import com.hackaboss.agenciaTurismo.model.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomBookingRepository extends JpaRepository<RoomBooking, Integer> {
}
