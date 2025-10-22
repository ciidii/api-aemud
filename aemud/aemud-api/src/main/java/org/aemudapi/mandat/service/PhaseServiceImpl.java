package org.aemudapi.mandat.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aemudapi.exceptions.customeExceptions.InvalidPhaseDatesException;
import org.aemudapi.mandat.dtos.CreatePhaseDto;
import org.aemudapi.mandat.dtos.PhaseDto;
import org.aemudapi.mandat.dtos.UpdatePhaseDto;
import org.aemudapi.mandat.entity.Mandat;
import org.aemudapi.mandat.entity.Phase;
import org.aemudapi.mandat.mapper.PhaseMapper;
import org.aemudapi.mandat.repository.MandatRepository;
import org.aemudapi.mandat.repository.PhaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PhaseServiceImpl implements PhaseService {

    private final PhaseRepository phaseRepository;
    private final MandatRepository mandatRepository;
    private final PhaseMapper phaseMapper;

    @Override
    public PhaseDto createPhase(CreatePhaseDto createPhaseDto) {
        Mandat mandat = mandatRepository.findById(createPhaseDto.mandatId())
                .orElseThrow(() -> new EntityNotFoundException("Mandat non trouvé avec l'id " + createPhaseDto.mandatId()));

        // Validate phase dates against mandate dates
        if (createPhaseDto.dateDebut().isBefore(mandat.getDateDebut()) || createPhaseDto.dateFin().isAfter(mandat.getDateFin())) {
            throw new InvalidPhaseDatesException("Les dates de début et de fin de la phase doivent être comprises entre les dates de début et de fin du mandat.");
        }

        Phase phase = phaseMapper.toEntity(createPhaseDto);
        phase.setMandat(mandat);

        Phase savedPhase = phaseRepository.save(phase);
        return phaseMapper.toDto(savedPhase);
    }

    @Override
    @Transactional(readOnly = true)
    public PhaseDto getPhaseById(String id) {
        Phase phase = phaseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Phase non trouvée avec l'id " + id));
        return phaseMapper.toDto(phase);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PhaseDto> getAllPhases() {
        return phaseRepository.findAll().stream()
                .map(phaseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PhaseDto updatePhase(String id, UpdatePhaseDto updatePhaseDto) {
        Phase existingPhase = phaseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Phase non trouvée avec l'id " + id));

        Mandat mandat = existingPhase.getMandat(); // Get the associated Mandat

        // Validate updated phase dates against mandate dates
        if (updatePhaseDto.dateDebut().isBefore(mandat.getDateDebut()) || updatePhaseDto.dateFin().isAfter(mandat.getDateFin())) {
            throw new InvalidPhaseDatesException("Les dates de début et de fin de la phase doivent être comprises entre les dates de début et de fin du mandat.");
        }

        phaseMapper.updateEntity(existingPhase, updatePhaseDto);
        Phase updatedPhase = phaseRepository.save(existingPhase);
        return phaseMapper.toDto(updatedPhase);
    }

    @Override
    public void deletePhase(String id) {
        if (!phaseRepository.existsById(id)) {
            throw new EntityNotFoundException("Phase non trouvée avec l'id " + id);
        }
        phaseRepository.deleteById(id);
    }
}
