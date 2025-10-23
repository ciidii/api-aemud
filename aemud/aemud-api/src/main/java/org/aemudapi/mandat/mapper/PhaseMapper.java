package org.aemudapi.mandat.mapper;

import org.aemudapi.mandat.dtos.CreatePhaseDto;
import org.aemudapi.mandat.dtos.PhaseDto;
import org.aemudapi.mandat.dtos.UpdatePhaseDto;
import org.aemudapi.mandat.entity.Phase;
import org.aemudapi.mandat.entity.PhaseStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PhaseMapper {

    public PhaseDto toDto(Phase phase) {
        if (phase == null) {
            return null;
        }

        LocalDate today = LocalDate.now();
        PhaseStatus status;

        if (phase.getDateFin().isBefore(today)) {
            status = PhaseStatus.PASSED;
        } else if (phase.getDateDebut().isAfter(today)) {
            status = PhaseStatus.FUTURE;
        } else {
            status = PhaseStatus.CURRENT;
        }

        return new PhaseDto(
                phase.getId(),
                phase.getNom(),
                phase.getDateDebut(),
                phase.getDateFin(),
                status
        );
    }

    public Phase toEntity(CreatePhaseDto createPhaseDto) {
        if (createPhaseDto == null) {
            return null;
        }
        Phase phase = new Phase();
        phase.setNom(createPhaseDto.nom());
        phase.setDateDebut(createPhaseDto.dateDebut());
        phase.setDateFin(createPhaseDto.dateFin());
        // Mandat will be set in the service layer
        return phase;
    }

    public void updateEntity(Phase phase, UpdatePhaseDto updatePhaseDto) {
        if (phase == null || updatePhaseDto == null) {
            return;
        }
        phase.setNom(updatePhaseDto.nom());
        phase.setDateDebut(updatePhaseDto.dateDebut());
        phase.setDateFin(updatePhaseDto.dateFin());
    }
}
