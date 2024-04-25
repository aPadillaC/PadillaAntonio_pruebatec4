package com.hackaboss.agenciaTurismo.repository;

import com.hackaboss.agenciaTurismo.model.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, Integer> {

    @Query("SELECT rb FROM RoomBooking rb WHERE rb.isCompleted = false AND rb.isDeleted = false")
    List<RoomBooking> findIncompleteAndNotDeletedBookings();

    @Query("SELECT rb FROM RoomBooking rb WHERE rb.room.id = :id AND rb.isDeleted = false")
    List<RoomBooking> findBookingsForRoomId(Integer id);

    @Query("SELECT rb FROM RoomBooking rb WHERE rb.id = :id AND rb.isDeleted = false")
    Optional<RoomBooking> findByIdAndNotDeleted(Integer id);

    @Query("SELECT rb FROM RoomBooking rb WHERE rb.room.id = :roomId AND rb.dateFrom < :dateTo AND rb.dateTo > :dateFrom AND rb.isDeleted = false")
    List<RoomBooking> findBookingsInDateRange(@Param("roomId") Integer roomId, @Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);
}
