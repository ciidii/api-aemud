package org.aemudapi.mandat.mapper;

import lombok.RequiredArgsConstructor;
import org.aemudapi.mandat.dtos.CreateMandatDto;
import org.aemudapi.mandat.dtos.MandatDto;
import org.aemudapi.mandat.dtos.UpdateMandatDto;
import org.aemudapi.mandat.entity.Mandat;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MandatMapper {

    private final PhaseMapper phaseMapper;

    public MandatDto toDto(Mandat mandat) {
        if (mandat == null) {
            return null;
        }
        return new MandatDto(
                mandat.getId(),
                mandat.getNom(),
                mandat.getDateDebut(),
                mandat.getDateFin(),
                mandat.isEstActif(),
                mandat.getPhases() != null ?
                        mandat.getPhases().stream().map(phaseMapper::toDto).collect(Collectors.toList()) :
                        Collections.emptyList()
        );
    }

    public Mandat toEntity(CreateMandatDto createMandatDto) {
        if (createMandatDto == null) {
            return null;
        }
        Mandat mandat = new Mandat();
        mandat.setNom(createMandatDto.nom());
        mandat.setDateDebut(createMandatDto.dateDebut());
        mandat.setDateFin(createMandatDto.dateFin());
        return mandat;
    }

    public void updateEntity(Mandat mandat, UpdateMandatDto updateMandatDto) {
        if (mandat == null || updateMandatDto == null) {
            return;
        }
        mandat.setNom(updateMandatDto.nom());
        mandat.setDateDebut(updateMandatDto.dateDebut());
        mandat.setDateFin(updateMandatDto.dateFin());
        mandat.setEstActif(updateMandatDto.estActif());
    }
}
