package com.example.etudiants_api.controller;

import com.example.etudiants_api.dto.DepartementDTO;
import com.example.etudiants_api.service.DepartementService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/departements")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DepartementController {

    private final DepartementService departementService;

    @GetMapping
    @Operation(summary = "Récupérer tous les départements")
    public ResponseEntity<List<DepartementDTO>> getAll() {
        return ResponseEntity.ok(departementService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un département par son ID")
    public ResponseEntity<DepartementDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(departementService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DepartementDTO> create(@Valid @RequestBody DepartementDTO dto) {
        return new ResponseEntity<>(departementService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartementDTO> update(@PathVariable Long id,
                                                 @Valid @RequestBody DepartementDTO dto) {
        return ResponseEntity.ok(departementService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}