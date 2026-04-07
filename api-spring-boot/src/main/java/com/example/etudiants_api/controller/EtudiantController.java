package com.example.etudiants_api.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.etudiants_api.repository.EtudiantRepository;
import com.example.etudiants_api.entity.Etudiant;
@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin(origins = "*")
public class EtudiantController {

    private final EtudiantRepository repo;

    public EtudiantController(EtudiantRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Etudiant> getAll() {
        return repo.findAll();
    }
}