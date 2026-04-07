package com.example.etudiants_api.entity;

import jakarta.persistence.*;
        import lombok.*;
        import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cin;
    private String nom;
    private LocalDate dateNaissance;
}