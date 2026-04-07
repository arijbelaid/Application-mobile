package com.example.etudiants_api.mapper;

import com.example.etudiants_api.dto.DepartementDTO;
import com.example.etudiants_api.entity.Departement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartementMapper {

    private final EtudiantMapper etudiantMapper;

    public DepartementDTO toDTO(Departement departement) {
        if (departement == null) return null;

        return DepartementDTO.builder()
                .id(departement.getId())
                .nom(departement.getNom())
                .etudiants(departement.getEtudiants() != null ?
                        departement.getEtudiants().stream().map(etudiantMapper::toDTO).toList() :
                        null)
                .build();
    }

    public Departement toEntity(DepartementDTO dto) {
        if (dto == null) return null;

        return Departement.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .build();
    }
}