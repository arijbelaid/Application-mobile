package com.example.etudiants_api.service;

import com.example.etudiants_api.dto.EtudiantDTO;
import com.example.etudiants_api.entity.Etudiant;
import com.example.etudiants_api.entity.Departement;
import com.example.etudiants_api.exception.ResourceNotFoundException;
import com.example.etudiants_api.mapper.EtudiantMapper;
import com.example.etudiants_api.repository.EtudiantRepository;
import com.example.etudiants_api.repository.DepartementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;
    private final DepartementRepository departementRepository;
    private final EtudiantMapper etudiantMapper;

    @Transactional(readOnly = true)
    public List<EtudiantDTO> findAll() {
        return etudiantRepository.findAll()
                .stream()
                .map(etudiantMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public EtudiantDTO findById(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant non trouvé avec l'id: " + id));
        return etudiantMapper.toDTO(etudiant);
    }

    @Transactional(readOnly = true)
    public List<EtudiantDTO> findByAnneeInscription(int annee) {
        return etudiantRepository.findByAnneePremiereInscription(annee)
                .stream()
                .map(etudiantMapper::toDTO)
                .toList();
    }

    public EtudiantDTO create(EtudiantDTO dto) {
        Departement departement = null;
        if (dto.getDepartementId() != null) {
            departement = departementRepository.findById(dto.getDepartementId())
                    .orElseThrow(() -> new ResourceNotFoundException("Département non trouvé"));
        }

        Etudiant etudiant = etudiantMapper.toEntity(dto, departement);
        etudiant = etudiantRepository.save(etudiant);
        return etudiantMapper.toDTO(etudiant);
    }

    public EtudiantDTO update(Long id, EtudiantDTO dto) {
        Etudiant existing = etudiantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant non trouvé"));

        Departement departement = null;
        if (dto.getDepartementId() != null) {
            departement = departementRepository.findById(dto.getDepartementId())
                    .orElseThrow(() -> new ResourceNotFoundException("Département non trouvé"));
        }

        existing.setCin(dto.getCin());
        existing.setNom(dto.getNom());
        existing.setDateNaissance(dto.getDateNaissance());
        existing.setEmail(dto.getEmail());
        existing.setAnneePremiereInscription(dto.getAnneePremiereInscription());
        existing.setDepartement(departement);

        existing = etudiantRepository.save(existing);
        return etudiantMapper.toDTO(existing);
    }

    public void delete(Long id) {
        if (!etudiantRepository.existsById(id)) {
            throw new ResourceNotFoundException("Étudiant non trouvé");
        }
        etudiantRepository.deleteById(id);
    }
}