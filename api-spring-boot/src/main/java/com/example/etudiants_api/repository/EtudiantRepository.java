package com.example.etudiants_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.etudiants_api.entity.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    List<Etudiant> findByAnneePremiereInscription(int annee);

}
