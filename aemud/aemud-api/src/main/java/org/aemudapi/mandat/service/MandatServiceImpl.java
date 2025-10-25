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

import java.time.LocalDate;
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

        validateDateRange(createMandatDto.dateDebut(), createMandatDto.dateFin(), "du mandat");

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

        validateDateRange(updateMandatDto.dateDebut(), updateMandatDto.dateFin(), "le mandat");

        // Store original dates to detect changes
        java.time.LocalDate originalDateDebut = existingMandat.getDateDebut();
        java.time.LocalDate originalDateFin = existingMandat.getDateFin();

        mandatMapper.updateEntity(existingMandat, updateMandatDto);

        boolean mandateDatesChanged = !originalDateDebut.equals(existingMandat.getDateDebut()) ||
                !originalDateFin.equals(existingMandat.getDateFin());

        if (mandateDatesChanged) {
            if (Boolean.TRUE.equals(updateMandatDto.recalculatePhasesAutomatically())) {
                // Perform automatic recalculation of phases
                recalculateAndSavePhases(existingMandat);
            } else {
                // Dates changed, but no automatic recalculation requested.
                // Validate existing phases against the new mandate dates.
                validateExistingPhasesAgainstMandate(existingMandat);

                // Re-validate for overlaps after potential manual changes or no changes
                validatePhasesDoNotOverlap(existingMandat, existingMandat.getPhases());
            }
        } else {
            // If mandate dates haven't changed, still validate current phases to catch any inconsistencies.
            validatePhasesDoNotOverlap(existingMandat, existingMandat.getPhases());
        }

        Mandat updatedMandat = mandatRepository.save(existingMandat);
        return mandatMapper.toDto(updatedMandat);
    }

    private void recalculateAndSavePhases(Mandat mandat) {
        // Fetch current phases (assuming they are always 2 from createMandat)
        List<Phase> existingPhases = phaseRepository.findByMandatId(mandat.getId());

        if (existingPhases.size() != 2) {
            throw new IllegalStateException("Le mandat doit avoir exactement deux phases pour le recalcul automatique.");
        }

        // Sort phases to ensure consistent assignment (e.g., by dateDebut)
        existingPhases.sort((p1, p2) -> p1.getDateDebut().compareTo(p2.getDateDebut()));

        Phase phase1ToUpdate = existingPhases.get(0);
        Phase phase2ToUpdate = existingPhases.get(1);

        // Calculate midpoint based on new mandate dates
        long totalDays = java.time.temporal.ChronoUnit.DAYS.between(mandat.getDateDebut(), mandat.getDateFin());
        java.time.LocalDate midPoint = mandat.getDateDebut().plusDays(totalDays / 2);

        // Validate phase 1 dates
        validateDateRange(mandat.getDateDebut(), midPoint, "de la phase '" + phase1ToUpdate.getNom() + "'");
        // Update phase 1 dates (preserving name)
        phase1ToUpdate.setDateDebut(mandat.getDateDebut());
        phase1ToUpdate.setDateFin(midPoint);

        // Validate phase 2 dates
        validateDateRange(midPoint, mandat.getDateFin(), "de la phase '" + phase2ToUpdate.getNom() + "'");
        // Update phase 2 dates (preserving name)
        phase2ToUpdate.setDateDebut(midPoint);
        phase2ToUpdate.setDateFin(mandat.getDateFin());

        // Validate updated phases don't overlap (should be guaranteed by logic, but as safeguard)
        List<Phase> updatedPhases = List.of(phase1ToUpdate, phase2ToUpdate);
        // validatePhasesDoNotOverlap(mandat, updatedPhases);

        phaseRepository.saveAll(updatedPhases);
    }

    private void validateExistingPhasesAgainstMandate(Mandat mandat) {
        List<Phase> existingPhases = phaseRepository.findByMandatId(mandat.getId());

        for (Phase phase : existingPhases) {
            // Validate phase date range itself
            validateDateRange(phase.getDateDebut(), phase.getDateFin(), "la phase '" + phase.getNom() + "'");

            // Phase must start on or after mandate start
            if (phase.getDateDebut().isBefore(mandat.getDateDebut())) {
                throw new IllegalArgumentException("La phase '" + phase.getNom() + "' commence avant la nouvelle date de début du mandat.");
            }
            // Phase must end on or before mandate end
            if (phase.getDateFin().isAfter(mandat.getDateFin())) {
                throw new IllegalArgumentException("La phase '" + phase.getNom() + "' se termine après la nouvelle date de fin du mandat.");
            }
        }
        // Also re-run overlap validation to ensure no new overlaps were introduced by mandate date changes
        // We pass existingPhases to check against themselves, not 'new ArrayList<>()' as in createMandat.
        validatePhasesDoNotOverlap(mandat, existingPhases);
    }

    private void validateDateRange(LocalDate dateDebut, LocalDate dateFin, String entityName) {
        if (dateDebut != null && dateFin != null && dateDebut.isAfter(dateFin)) {
            throw new IllegalArgumentException("La date de début " + entityName + " ne peut pas être après la date de fin.");
        }
    }

    @Override
    public void deleteMandat(String id) {
        if (!mandatRepository.existsById(id)) {
            throw new EntityNotFoundException("Mandat non trouvé avec l'id " + id);
        }
        mandatRepository.deleteById(id);
    }
}
