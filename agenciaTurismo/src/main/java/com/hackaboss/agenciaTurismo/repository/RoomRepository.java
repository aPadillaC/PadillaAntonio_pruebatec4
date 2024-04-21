package com.hackaboss.agenciaTurismo.repository;

import com.hackaboss.agenciaTurismo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT r FROM Room r WHERE r.id = :id AND r.isDeleted = false")
    Optional<Room> findByIdAndNotDeleted(Integer id);
}
