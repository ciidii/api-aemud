package org.aemudapi.mandat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aemudapi.mandat.dtos.CreateMandatDto;
import org.aemudapi.mandat.dtos.MandatDto;
import org.aemudapi.mandat.dtos.UpdateMandatDto;
import org.aemudapi.mandat.service.MandatService;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mandats")
@RequiredArgsConstructor
public class MandatController {

    private final MandatService mandatService;

    @GetMapping
    public ResponseEntity<ResponseVO<List<MandatDto>>> getAllMandats() {
        List<MandatDto> mandats = mandatService.getAllMandats();
        return new ResponseVOBuilder<List<MandatDto>>()
                .addData(mandats)
                .build()
                .toResponseEntity();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseVO<MandatDto>> getMandatById(@PathVariable String id) {
        MandatDto mandat = mandatService.getMandatById(id);
        return new ResponseVOBuilder<MandatDto>()
                .addData(mandat)
                .build()
                .toResponseEntity();
    }

    @PostMapping
    public ResponseEntity<ResponseVO<MandatDto>> createMandat(@Valid @RequestBody CreateMandatDto createMandatDto) {
        MandatDto createdMandat = mandatService.createMandat(createMandatDto);
        return new ResponseVOBuilder<MandatDto>()
                .addData(createdMandat)
                .build()
                .toResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseVO<MandatDto>> updateMandat(@PathVariable String id, @Valid @RequestBody UpdateMandatDto updateMandatDto) {
        MandatDto updatedMandat = mandatService.updateMandat(id, updateMandatDto);
        return new ResponseVOBuilder<MandatDto>()
                .addData(updatedMandat)
                .build()
                .toResponseEntity();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseVO<Void>> deleteMandat(@PathVariable String id) {
        mandatService.deleteMandat(id);
        return new ResponseVOBuilder<Void>()
                .success()
                .build()
                .toResponseEntity(HttpStatus.NO_CONTENT); // 204 No Content for successful deletion
    }
}
