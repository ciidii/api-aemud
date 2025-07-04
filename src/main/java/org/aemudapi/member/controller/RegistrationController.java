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

    @GetMapping("registration-peer-session")
    public ResponseEntity<ResponseVO<Integer>> getRegistrationBySession(@RequestParam("session") String session) {
        return this.registrationService.getRegistrationCountBySession(session);
    }

    @GetMapping("new-inscription-session")
    public ResponseEntity<ResponseVO<Integer>> getNewOrRenewalAdherentForASession(@RequestParam("session") String session, @RequestParam("typeInscription") TypeInscription typeInscription) {
        return this.registrationService.getNewOrRenewalAdherentForASession(session, typeInscription);
    }

    @GetMapping("payed-or-no-payed")
    public ResponseEntity<ResponseVO<Integer>> getPayedOrNoPayedSessionCountPeerSession(@RequestParam("session") String session, @RequestParam("statusPayment") Boolean statusPayment) {
        return this.registrationService.getPayedOrNoPayedSessionCountPeerSession(session, statusPayment);
    }

    @GetMapping("member-by-session")
    public ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> getMemberBySession(@RequestParam("session") String session) {
        return this.registrationService.getMemberBySession(session);
    }

    @GetMapping("members-payment-status")
    ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> getPayedOrNoPayedMembersPeerSession(@RequestParam("session") String session, @RequestParam("statusPayment") Boolean statusPayment) {
        return this.registrationService.getPayedOrNoPayedMembersPeerSession(session, statusPayment);
    }

    @GetMapping("members-registration-status")
    ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> getMembersRegistrationsStatusForSessions(@RequestParam("session") String session, @RequestParam("registrationStatus") RegistrationStatus registrationStatus) {
        return this.registrationService.getMembersRegistrationsStatusForSessions(session, registrationStatus);
    }

    @GetMapping("members-by-registration-year")
    ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> getMemberByYearOfRegistration(@RequestParam("session") String session) {
        return this.registrationService.getMemberByYearOfRegistration(session);
    }
}
