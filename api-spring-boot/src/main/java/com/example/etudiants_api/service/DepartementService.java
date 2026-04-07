package com.example.etudiants_api.service;

import com.example.etudiants_api.dto.DepartementDTO;
import com.example.etudiants_api.entity.Departement;
import com.example.etudiants_api.exception.ResourceNotFoundException;
import com.example.etudiants_api.mapper.DepartementMapper;
import com.example.etudiants_api.repository.DepartementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartementService {

    private final DepartementRepository departementRepository;
    private final DepartementMapper departementMapper;

    @Transactional(readOnly = true)
    public List<DepartementDTO> findAll() {
        return departementRepository.findAll()
                .stream()
                .map(departementMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public DepartementDTO findById(Long id) {
        Departement departement = departementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Département non trouvé"));
        return departementMapper.toDTO(departement);
    }

    public DepartementDTO create(DepartementDTO dto) {
        Departement departement = departementMapper.toEntity(dto);
        departement = departementRepository.save(departement);
        return departementMapper.toDTO(departement);
    }

    public DepartementDTO update(Long id, DepartementDTO dto) {
        Departement existing = departementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Département non trouvé"));

        existing.setNom(dto.getNom());
        existing = departementRepository.save(existing);
        return departementMapper.toDTO(existing);
    }

    public void delete(Long id) {
        if (!departementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Département non trouvé");
        }
        departementRepository.deleteById(id);
    }
}