package org.aemudapi.member.controller;


import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.MemberDataResponseDTO;
import org.aemudapi.member.dtos.RegistrationRequestDto;
import org.aemudapi.member.dtos.RegistrationRequestWithPhoneNumberDto;
import org.aemudapi.member.entity.RegistrationStatus;
import org.aemudapi.member.entity.TypeInscription;
import org.aemudapi.member.service.RegistrationService;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping(path = "registration")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;


    @PostMapping
    public ResponseEntity<ResponseVO<Void>> register(@RequestBody RegistrationRequestDto registrationRequestDto) {
        return this.registrationService.
                registerMember(registrationRequestDto);
    }

    @PostMapping("number-phone")
    public ResponseEntity<ResponseVO<Void>> register(@RequestBody RegistrationRequestWithPhoneNumberDto registrationRequestDto) {
        return this.registrationService.registerMemberWithNumberPhone(registrationRequestDto);
    }

    @PutMapping
    public ResponseEntity<ResponseVO<Void>> updateRegistration(@RequestBody RegistrationRequestDto registrationRequestDto) {
        return this.registrationService.registerMember(registrationRequestDto);
    }

    @DeleteMapping
    public ResponseEntity<ResponseVO<Void>> deleteRegistration(@RequestParam("id") String id) {
        return this.registrationService.deleteRegistration(id);
    }
}
