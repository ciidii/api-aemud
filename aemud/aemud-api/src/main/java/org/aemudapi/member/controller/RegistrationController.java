package org.aemudapi.member.controller;


import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.RegistrationRequestDto;
import org.aemudapi.member.dtos.RegistrationRequestWithPhoneNumberDto;
import org.aemudapi.member.service.RegistrationService;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "registration")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;


    @PostMapping("/{phaseId}")
    public ResponseEntity<ResponseVO<Void>> register(@RequestBody RegistrationRequestDto registrationRequestDto, @PathVariable String phaseId) {
        return this.registrationService.
                registerMember(registrationRequestDto, phaseId);
    }

    @PostMapping("number-phone")
    public ResponseEntity<ResponseVO<Void>> register(@RequestBody RegistrationRequestWithPhoneNumberDto registrationRequestDto) {
        return this.registrationService.registerMemberWithNumberPhone(registrationRequestDto);
    }

    @PutMapping("/{phaseId}")
    public ResponseEntity<ResponseVO<Void>> updateRegistration(@RequestBody RegistrationRequestDto registrationRequestDto, @PathVariable String phaseId) {
        return this.registrationService.registerMember(registrationRequestDto, phaseId);
    }

    @DeleteMapping
    public ResponseEntity<ResponseVO<Void>> deleteRegistration(@RequestParam("id") String id) {
        return this.registrationService.deleteRegistration(id);
    }
}
