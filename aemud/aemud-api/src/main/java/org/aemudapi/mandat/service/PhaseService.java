package org.aemudapi.mandat.service;

import org.aemudapi.mandat.dtos.CreatePhaseDto;
import org.aemudapi.mandat.dtos.PhaseDto;
import org.aemudapi.mandat.dtos.UpdatePhaseDto;

import java.util.List;

public interface PhaseService {
    PhaseDto createPhase(CreatePhaseDto createPhaseDto);
    PhaseDto getPhaseById(String id);
    List<PhaseDto> getAllPhases();
    PhaseDto updatePhase(String id, UpdatePhaseDto updatePhaseDto);
    void deletePhase(String id);
}
