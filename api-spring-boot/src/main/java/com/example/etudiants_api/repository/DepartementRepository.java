package com.example.etudiants_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.etudiants_api.entity.Departement;
import java.util.Optional;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
    Optional<Departement> findByNom(String nom);
}