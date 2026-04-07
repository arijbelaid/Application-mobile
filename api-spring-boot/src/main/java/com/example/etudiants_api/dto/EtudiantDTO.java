package com.example.etudiants_api.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantDTO {
    private Long id;
    private String cin;
    private String nom;
    private LocalDate dateNaissance;
    private String email;
    private int anneePremiereInscription;
    private Long departementId;
    private String departementNom;
    private Integer age;
}