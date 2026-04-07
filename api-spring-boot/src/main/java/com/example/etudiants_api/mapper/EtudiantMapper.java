package com.example.etudiants_api.mapper;

import com.example.etudiants_api.dto.EtudiantDTO;
import com.example.etudiants_api.entity.Etudiant;
import com.example.etudiants_api.entity.Departement;
import org.springframework.stereotype.Component;

@Component
public class EtudiantMapper {

    public EtudiantDTO toDTO(Etudiant etudiant) {
        if (etudiant == null) return null;

        return EtudiantDTO.builder()
                .id(etudiant.getId())
                .cin(etudiant.getCin())
                .nom(etudiant.getNom())
                .dateNaissance(etudiant.getDateNaissance())
                .email(etudiant.getEmail())
                .anneePremiereInscription(etudiant.getAnneePremiereInscription())
                .departementId(etudiant.getDepartement() != null ? etudiant.getDepartement().getId() : null)
                .departementNom(etudiant.getDepartement() != null ? etudiant.getDepartement().getNom() : null)
                .age(etudiant.age())
                .build();
    }

    public Etudiant toEntity(EtudiantDTO dto, Departement departement) {
        if (dto == null) return null;

        return Etudiant.builder()
                .id(dto.getId())
                .cin(dto.getCin())
                .nom(dto.getNom())
                .dateNaissance(dto.getDateNaissance())
                .email(dto.getEmail())
                .anneePremiereInscription(dto.getAnneePremiereInscription())
                .departement(departement)
                .build();
    }
}