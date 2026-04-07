package com.example.etudiants_api.dto;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartementDTO {
    private Long id;
    private String nom;
    private List<EtudiantDTO> etudiants;
}