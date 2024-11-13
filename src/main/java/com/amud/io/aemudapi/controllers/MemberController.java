package com.amud.io.aemudapi.controllers;

import com.amud.io.aemudapi.dto.MemberDataResponseDTO;
import com.amud.io.aemudapi.dto.MemberRequestDto;
import com.amud.io.aemudapi.dto.MessageDto;
import com.amud.io.aemudapi.services.MemberService;
import com.amud.io.aemudapi.services.OrangeSmsService;
import com.amud.io.aemudapi.utils.RequestPageableVO;
import com.amud.io.aemudapi.utils.ResponsePageableVO;
import com.amud.io.aemudapi.utils.ResponseVO;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController()
@RequestMapping(path = "members")
@CrossOrigin(origins = "http://localhost:4200")
public class MemberController {
    private final MemberService memberService;
    private final OrangeSmsService orangeSmsService;

    public MemberController(MemberService memberService, OrangeSmsService orangeSmsService) {
        this.memberService = memberService;
        this.orangeSmsService = orangeSmsService;
    }

    @PostMapping
    ResponseEntity<ResponseVO<MemberRequestDto>> addMember(@RequestBody MemberRequestDto memberRequestDto, @RequestParam("backup") boolean backup) throws GeneralSecurityException, IOException {
        return this.memberService.addMember(memberRequestDto, backup);
    }

    @GetMapping(path = "all")
    ResponseEntity<ResponsePageableVO<MemberDataResponseDTO>> getMembers(@RequestParam("page") @Min(1) int page, @Min(1) @RequestParam("rpp") int rpp) {
        RequestPageableVO requestPageableVO = new RequestPageableVO(page, rpp);
        return this.memberService.getAllMembers(requestPageableVO);
    }

    @GetMapping
    ResponseEntity<ResponseVO<MemberDataResponseDTO>> getMember(@RequestParam("member-id") Long memberID) {
        return this.memberService.getMemberById(memberID);
    }


    @PutMapping
    ResponseEntity<ResponseVO<MemberRequestDto>> updateMember(@RequestBody MemberRequestDto memberRequestDto) throws GeneralSecurityException, IOException {
        return this.memberService.updateMember(memberRequestDto);
    }

    @DeleteMapping
    ResponseEntity<ResponseVO<Void>> removeMember(@RequestParam("userId") Long userId) {
        return this.memberService.removeMember(userId);
    }

    @PostMapping(path = "sms")
    ResponseEntity<String> sendMessage(@RequestBody MessageDto message) throws IOException {
        this.orangeSmsService.sendSmsToMultipleRecipients(message.getRecipientNumbers(), message.getMessage());
        return new ResponseEntity<>("Message envoyer avec succes", HttpStatus.OK);
    }
}

