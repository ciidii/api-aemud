package org.aemudapi.mandat.mapper;

import org.aemudapi.mandat.dtos.PhaseDto;
import org.aemudapi.mandat.entity.Phase;
import org.springframework.stereotype.Component;

@Component
public class PhaseMapper {

    public PhaseDto toDto(Phase phase) {
        if (phase == null) {
            return null;
        }
        return new PhaseDto(
            phase.getId(),
            phase.getNom(),
            phase.getDateDebut(),
            phase.getDateFin()
        );
    }

}
