package org.aemudapi.member.controller;

import jakarta.validation.constraints.Min;
import org.aemudapi.member.dtos.FilterDTO;
import org.aemudapi.member.dtos.MemberDataResponseDTO;
import org.aemudapi.member.dtos.MemberRequestDto;
import org.aemudapi.notification.dtos.MessageDto;
import org.aemudapi.member.service.MemberService;
import org.aemudapi.notification.services.OrangeSmsService;
import org.aemudapi.utils.RequestPageableVO;
import org.aemudapi.utils.ResponsePageableVO;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController()
@RequestMapping(path = "members")
public class MemberController {
    private final MemberService memberService;
    private final OrangeSmsService orangeSmsService;

    public MemberController(MemberService memberService, OrangeSmsService orangeSmsService) {
        this.memberService = memberService;
        this.orangeSmsService = orangeSmsService;
    }

    @PostMapping
    ResponseEntity<ResponseVO<MemberRequestDto>> addMember(@RequestBody MemberRequestDto memberRequestDto) {
        return this.memberService.addMember(memberRequestDto);
    }

    @GetMapping(path = "all")
    ResponseEntity<ResponsePageableVO<MemberDataResponseDTO>> getMembers(@RequestParam("page") @Min(1) int page, @Min(1) @RequestParam("rpp") int rpp) {
        RequestPageableVO requestPageableVO = new RequestPageableVO(page, rpp);
        return this.memberService.getAllMembers(requestPageableVO);
    }

    @GetMapping
    ResponseEntity<ResponseVO<MemberDataResponseDTO>> getMember(@RequestParam("member-id") String memberID) {
        return this.memberService.getMemberById(memberID);
    }


    @PutMapping
    ResponseEntity<ResponseVO<MemberRequestDto>> updateMember(@RequestBody MemberRequestDto memberRequestDto) throws GeneralSecurityException, IOException {
        return this.memberService.updateMember(memberRequestDto);
    }

    @DeleteMapping
    ResponseEntity<ResponseVO<Void>> removeMember(@RequestParam("userId") String userId) {
        return this.memberService.removeMember(userId);
    }

    @PostMapping(path = "sms")
    ResponseEntity<String> sendMessage(@RequestBody MessageDto message) throws IOException {
        this.orangeSmsService.sendSmsToMultipleRecipients(message.getRecipientNumbers(), message.getMessage());
        return new ResponseEntity<>("Message envoyer avec succes", HttpStatus.OK);
    }

    @GetMapping(path = "search")
    ResponseEntity<ResponsePageableVO<MemberDataResponseDTO>> searchMember
            (@RequestParam("page") @Min(1) int page,
             @Min(1) @RequestParam("rpp") int rpp,
             @RequestParam(name = "criteria", required = false) String criteria,
             @RequestParam(name = "value", required = false) String value,
             @RequestParam(name = "club", required = false) String club,
             @RequestParam(name = "commission", required = false) String commission,
             @RequestParam(name = "year", required = false) String year
            ) {
        RequestPageableVO requestPageableVO = new RequestPageableVO(page, rpp);
        FilterDTO filters = new FilterDTO(club, year, commission);
        return this.memberService.searchMember(requestPageableVO, criteria, value, filters);
    }

    @GetMapping(path = "print")
    ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> searchMemberToPrint(
            @RequestParam(name = "criteria", required = false) String criteria,
            @RequestParam(name = "value", required = false) String value,
            @RequestParam(name = "club", required = false) String club,
            @RequestParam(name = "commission", required = false) String commission,
            @RequestParam(name = "year", required = false) String year
    ) {
        FilterDTO filters = new FilterDTO(club, year, commission);
        return this.memberService.searchMemberToPrint(criteria, value, filters);
    }
}