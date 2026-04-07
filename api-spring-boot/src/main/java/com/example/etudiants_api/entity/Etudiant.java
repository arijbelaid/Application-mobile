package com.example.etudiants_api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cin;

    private String nom;
    private LocalDate dateNaissance;

    @Column(unique = true)
    private String email;

    private int anneePremiereInscription;

    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    public int age() {
        if (this.dateNaissance == null) return 0;
        return Period.between(this.dateNaissance, LocalDate.now()).getYears();
    }
}