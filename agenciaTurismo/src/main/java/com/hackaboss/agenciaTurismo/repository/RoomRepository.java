package com.hackaboss.agenciaTurismo.repository;

import com.hackaboss.agenciaTurismo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT r FROM Room r WHERE r.id = :id AND r.isDeleted = false")
    Optional<Room> findByIdAndNotDeleted(Integer id);

    @Query("SELECT r FROM Room r WHERE r.hotel.city = :city AND r.dateFrom <= :dateFrom AND r.dateTo >= :dateTo AND r.isDeleted = false AND r.isBooked = false")
    Optional<List<Room>> findByCityAndDate(String city, LocalDate dateFrom,  LocalDate dateTo);

}
