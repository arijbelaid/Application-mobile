package com.example.etudiants_api.controller;

import com.example.etudiants_api.dto.EtudiantDTO;
import com.example.etudiants_api.service.EtudiantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EtudiantController {

    private final EtudiantService etudiantService;

    @GetMapping
    @Operation(summary = "Récupérer tous les étudiants")
    public ResponseEntity<List<EtudiantDTO>> getAll(
            @RequestParam(required = false) Integer annee) {
        if (annee != null) {
            return ResponseEntity.ok(etudiantService.findByAnneeInscription(annee));
        }
        return ResponseEntity.ok(etudiantService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un étudiant par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Étudiant trouvé"),
            @ApiResponse(responseCode = "404", description = "Étudiant non trouvé")
    })
    public ResponseEntity<EtudiantDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(etudiantService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Créer un nouvel étudiant")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EtudiantDTO> create(@Valid @RequestBody EtudiantDTO etudiantDTO) {
        return new ResponseEntity<>(etudiantService.create(etudiantDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un étudiant")
    public ResponseEntity<EtudiantDTO> update(@PathVariable Long id,
                                              @Valid @RequestBody EtudiantDTO etudiantDTO) {
        return ResponseEntity.ok(etudiantService.update(id, etudiantDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un étudiant")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        etudiantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}