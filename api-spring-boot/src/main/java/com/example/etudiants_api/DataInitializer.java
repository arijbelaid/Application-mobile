package com.example.etudiants_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final EtudiantRepository repo;

    public DataInitializer(EtudiantRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        if (repo.count() == 0) {
            repo.save(new Etudiant(null, "12345678", "Ali Ben Salah", LocalDate.of(2000, 3, 15)));
            repo.save(new Etudiant(null, "23456789", "Sana Trabelsi", LocalDate.of(2001, 7, 22)));
            repo.save(new Etudiant(null, "34567890", "Omar Hamdi", LocalDate.of(1999, 11, 5)));
            repo.save(new Etudiant(null, "45678901", "Rim Chaabane", LocalDate.of(2002, 1, 30)));
            repo.save(new Etudiant(null, "56789012", "Yassine Mzoughi", LocalDate.of(2000, 9, 18)));
        }
    }
}
