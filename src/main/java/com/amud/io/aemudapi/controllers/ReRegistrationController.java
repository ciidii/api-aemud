package com.amud.io.aemudapi.controllers;

import com.amud.io.aemudapi.dto.ReRegistrationDto;
import com.amud.io.aemudapi.dto.RegistrationPearYearDto;
import com.amud.io.aemudapi.dto.RegistrationResponseDto;
import com.amud.io.aemudapi.services.ReRegistrationService;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("re-registration")
public class ReRegistrationController {
    private final ReRegistrationService reRegistrationService;

    public ReRegistrationController(ReRegistrationService reRegistrationService) {
        this.reRegistrationService = reRegistrationService;
    }

    @PostMapping()
    public ResponseEntity<ResponseVO<RegistrationResponseDto>> reRegister(@RequestBody ReRegistrationDto reRegistrationDto) {
        return this.reRegistrationService.reRegisterMember(reRegistrationDto);
    }

    @GetMapping("all")
    public ResponseEntity<ResponseVO<RegistrationPearYearDto>> getAllRegisteredForAYear(@RequestParam("year") Long year) {
        return this.reRegistrationService.getAllReRegisterForAYear(year);
    }

    @GetMapping("not-all")
    public ResponseEntity<ResponseVO<RegistrationPearYearDto>> getAllNotRegisteredForAYear(@RequestParam("year") Long year) {
        return this.reRegistrationService.getAllNotReRegisterForAYear(year);
    }

}
