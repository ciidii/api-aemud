package org.aemudapi.notification.controller;

import lombok.RequiredArgsConstructor;
import org.aemudapi.notification.dtos.SmsModelDTO;
import org.aemudapi.notification.services.SmsModelService;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("smsmodel")
@RequiredArgsConstructor
public class SmsModelController {

    private final SmsModelService service;

    @PostMapping
    public ResponseEntity<ResponseVO<SmsModelDTO>> create(@RequestBody SmsModelDTO dto) {
        return service.addSmsModel(dto);
    }

    @GetMapping("all")
    public ResponseEntity<ResponseVO<List<SmsModelDTO>>> getAll() {
        return service.getAllSmsModels();
    }

    @GetMapping
    public ResponseEntity<ResponseVO<SmsModelDTO>> getById(@RequestParam("id") String id) {
        return service.findSmsModelById(id);
    }

    @DeleteMapping
    public ResponseEntity<ResponseVO<Void>> delete(@RequestParam("id") String id) {
        return service.deleteSmsModel(id);
    }
}