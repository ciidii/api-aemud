package org.aemudapi.mandat.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aemudapi.mandat.dtos.CreateMandatDto;
import org.aemudapi.mandat.dtos.MandatDto;
import org.aemudapi.mandat.entity.Mandat;
import org.aemudapi.mandat.entity.Phase;
import org.aemudapi.mandat.mapper.MandatMapper;
import org.aemudapi.mandat.repository.MandatRepository;
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
        Phase phase1 = new Phase();
        phase1.setNom("Année 1");
        phase1.setDateDebut(mandat.getDateDebut());
        phase1.setDateFin(mandat.getDateDebut().plusYears(1));
        phase1.setMandat(mandat);
        phases.add(phase1);

        Phase phase2 = new Phase();
        phase2.setNom("Année 2");
        phase2.setDateDebut(mandat.getDateDebut().plusYears(1));
        // Assurons-nous que la date de fin de la phase 2 correspond à la date de fin du mandat
        phase2.setDateFin(mandat.getDateFin());
        phase2.setMandat(mandat);
        phases.add(phase2);

        mandat.setPhases(phases);

        Mandat savedMandat = mandatRepository.save(mandat);
        return mandatMapper.toDto(savedMandat);
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
