package com.hackaboss.agenciaTurismo.repository;

import com.hackaboss.agenciaTurismo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
}
