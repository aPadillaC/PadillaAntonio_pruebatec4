package com.hackaboss.agenciaTurismo.repository;

import com.hackaboss.agenciaTurismo.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> findByNameAndCity(String name, String city);

    @Query("SELECT h FROM Hotel h WHERE h.id = :id AND h.isDeleted = false")
    Optional<Hotel> findByIdAndNotDeleted(Integer id);

    @Query("SELECT h FROM Hotel h WHERE h.isDeleted = false")
    List<Hotel> findAllNotDeleted();


}