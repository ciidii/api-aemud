package org.aemudapi.member.controller;


import org.aemudapi.member.dtos.MemberDataRequestDTO;
import org.aemudapi.member.service.InscriptionService;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "main-form")
public class InscriptionController {
    private final InscriptionService inscriptionService;

    public InscriptionController(InscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    @PostMapping("add")
    public ResponseEntity<ResponseVO<Void>> register(@RequestBody MemberDataRequestDTO memberDataRequestDTO) {
        return this.inscriptionService.registerMember(memberDataRequestDTO);
    }
}
