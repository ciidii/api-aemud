package org.aemudapi.mandat.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aemudapi.mandat.dtos.CreateMandatDto;
import org.aemudapi.mandat.dtos.MandatDto;
import org.aemudapi.mandat.dtos.UpdateMandatDto;
import org.aemudapi.mandat.entity.Mandat;
import org.aemudapi.mandat.entity.Phase;
import org.aemudapi.mandat.mapper.MandatMapper;
import org.aemudapi.mandat.repository.MandatRepository;
import org.aemudapi.mandat.repository.PhaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MandatServiceImpl implements MandatService {

    private final MandatRepository mandatRepository;
    private final MandatMapper mandatMapper;
    private final PhaseRepository phaseRepository;
    @Override
    @Transactional(readOnly = true)
    public List<MandatDto> getAllMandats() {
        return mandatRepository.findAll().stream()
                .map(mandatMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MandatDto getMandatById(String id) {
        Mandat mandat = mandatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mandat non trouvé avec l'id " + id));
        return mandatMapper.toDto(mandat);
    }

    @Override
    public MandatDto createMandat(CreateMandatDto createMandatDto) {

        Mandat mandat = mandatMapper.toEntity(createMandatDto);
        mandat.setEstActif(false);

        List<Phase> phases = new ArrayList<>();

        // Calculer le point médian du mandat
        long totalDays = java.time.temporal.ChronoUnit.DAYS.between(mandat.getDateDebut(), mandat.getDateFin());
        java.time.LocalDate midPoint = mandat.getDateDebut().plusDays(totalDays / 2);

        Phase phase1 = new Phase();
        phase1.setNom("Année 1");
        phase1.setDateDebut(mandat.getDateDebut());
        phase1.setDateFin(midPoint);
        phase1.setMandat(mandat);
        phases.add(phase1);

        Phase phase2 = new Phase();
        phase2.setNom("Année 2");
        phase2.setDateDebut(midPoint);
        phase2.setDateFin(mandat.getDateFin());
        phase2.setMandat(mandat);
        phases.add(phase2);

        mandat.setPhases(phases);

        // Validate for overlaps before saving
        validatePhasesDoNotOverlap(mandat, phases);

        Mandat savedMandat = mandatRepository.save(mandat);
        return mandatMapper.toDto(savedMandat);
    }

    private void validatePhasesDoNotOverlap(Mandat mandat, List<Phase> newPhases) {
        // Fetch existing phases for the mandate
        List<Phase> existingPhases = mandat.getId() != null ? phaseRepository.findByMandatId(mandat.getId()) : new ArrayList<>();

        // Combine existing and new phases for validation
        List<Phase> allPhases = new ArrayList<>(existingPhases);
        allPhases.addAll(newPhases);

        // Check for overlaps within all phases
        for (int i = 0; i < allPhases.size(); i++) {
            for (int j = i + 1; j < allPhases.size(); j++) {
                Phase p1 = allPhases.get(i);
                Phase p2 = allPhases.get(j);

                // Overlap condition: (start1 <= end2) AND (start2 <= end1)
                if (p1.getDateDebut().isBefore(p2.getDateFin()) && p2.getDateDebut().isBefore(p1.getDateFin())) {
                    throw new IllegalArgumentException("Les phases se chevauchent : " + p1.getNom() + " (" + p1.getDateDebut() + " - " + p1.getDateFin() + ") et " + p2.getNom() + " (" + p2.getDateDebut() + " - " + p2.getDateFin() + ")");
                }
            }
        }
    }

    @Override
    public MandatDto updateMandat(String id, UpdateMandatDto updateMandatDto) {
        Mandat existingMandat = mandatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mandat non trouvé avec l'id " + id));

        mandatMapper.updateEntity(existingMandat, updateMandatDto);
        Mandat updatedMandat = mandatRepository.save(existingMandat);
        return mandatMapper.toDto(updatedMandat);
    }

    @Override
    public void deleteMandat(String id) {
        if (!mandatRepository.existsById(id)) {
            throw new EntityNotFoundException("Mandat non trouvé avec l'id " + id);
        }
        mandatRepository.deleteById(id);
    }
}
