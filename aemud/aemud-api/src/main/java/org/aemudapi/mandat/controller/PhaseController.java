package org.aemudapi.mandat.controller;

import lombok.RequiredArgsConstructor;
import org.aemudapi.mandat.dtos.CreatePhaseDto;
import org.aemudapi.mandat.dtos.PhaseDto;
import org.aemudapi.mandat.dtos.UpdatePhaseDto;
import org.aemudapi.mandat.service.PhaseService;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/phases")
@RequiredArgsConstructor
public class PhaseController {

    private final PhaseService phaseService;

    @PostMapping
    public ResponseEntity<ResponseVO<PhaseDto>> createPhase(@Valid @RequestBody CreatePhaseDto createPhaseDto) {
        PhaseDto createdPhase = phaseService.createPhase(createPhaseDto);
        return new ResponseVOBuilder<PhaseDto>()
                .addData(createdPhase)
                .build()
                .toResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseVO<PhaseDto>> getPhaseById(@PathVariable String id) {
        PhaseDto phase = phaseService.getPhaseById(id);
        return new ResponseVOBuilder<PhaseDto>()
                .addData(phase)
                .build()
                .toResponseEntity();
    }

    @GetMapping
    public ResponseEntity<ResponseVO<List<PhaseDto>>> getAllPhases() {
        List<PhaseDto> phases = phaseService.getAllPhases();
        return new ResponseVOBuilder<List<PhaseDto>>()
                .addData(phases)
                .build()
                .toResponseEntity();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseVO<PhaseDto>> updatePhase(@PathVariable String id, @Valid @RequestBody UpdatePhaseDto updatePhaseDto) {
        PhaseDto updatedPhase = phaseService.updatePhase(id, updatePhaseDto);
        return new ResponseVOBuilder<PhaseDto>()
                .addData(updatedPhase)
                .build()
                .toResponseEntity();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseVO<Void>> deletePhase(@PathVariable String id) {
        phaseService.deletePhase(id);
        return new ResponseVOBuilder<Void>()
                .success()
                .build()
                .toResponseEntity(HttpStatus.NO_CONTENT);
    }
}
