package com.hackaboss.agenciaTurismo.repository;

import com.hackaboss.agenciaTurismo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

    @Query("SELECT c FROM Client c WHERE c.nif = :nif AND c.isDeleted = false")
    Client findByNifAndNotDeleted(String nif);

}
