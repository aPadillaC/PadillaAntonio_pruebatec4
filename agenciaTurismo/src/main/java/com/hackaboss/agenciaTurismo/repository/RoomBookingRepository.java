package com.hackaboss.agenciaTurismo.repository;

import com.hackaboss.agenciaTurismo.model.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomBookingRepository extends JpaRepository<RoomBooking, Integer> {

    @Query("SELECT rb FROM RoomBooking rb WHERE rb.isCompleted = false AND rb.isDeleted = false")
    List<RoomBooking> findIncompleteAndNotDeletedBookings();
}
