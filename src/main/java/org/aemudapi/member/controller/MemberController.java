package org.aemudapi.member.controller;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.FilterDTO;
import org.aemudapi.member.dtos.MemberDataResponseDTO;
import org.aemudapi.member.dtos.MemberRequestDto;
import org.aemudapi.member.entity.RegistrationStatus;
import org.aemudapi.member.entity.TypeInscription;
import org.aemudapi.member.service.MemberService;
import org.aemudapi.utils.RequestPageableVO;
import org.aemudapi.utils.ResponsePageableVO;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "members")
@AllArgsConstructor
public class MemberController {
    private final MemberService memberService;


    @PostMapping
    ResponseEntity<ResponseVO<MemberDataResponseDTO>> addMember(@RequestBody MemberRequestDto memberRequestDto) {
        return this.memberService.addMember(memberRequestDto);
    }

    @GetMapping
    ResponseEntity<ResponseVO<MemberDataResponseDTO>> getMember(@RequestParam("member-id") String memberID) {
        return this.memberService.getMemberById(memberID);
    }

    @GetMapping("numberphone")
    ResponseEntity<ResponseVO<MemberDataResponseDTO>> findMemberByNumberPhone(@RequestParam("numberphone") String numberphone) {
        return this.memberService.findMemberByNumberPhone(numberphone);
    }


    @PutMapping
    ResponseEntity<ResponseVO<MemberDataResponseDTO>> updateMember(@RequestBody MemberRequestDto memberRequestDto) {
        return this.memberService.updateMember(memberRequestDto);
    }

    @DeleteMapping
    ResponseEntity<ResponseVO<Void>> removeMember(@RequestParam("memberId") String memberId) {
        return this.memberService.removeMember(memberId);
    }

    @GetMapping(path = "search")
    ResponseEntity<ResponsePageableVO<MemberDataResponseDTO>> searchMember(
            @RequestParam("page") @Min(1) int page,
            @Min(1) @RequestParam("rpp") int rpp,
            @RequestParam(name = "criteria", required = false) String criteria,
            @RequestParam(name = "value", required = false) String value,
            @RequestParam(name = "club", required = false) String club,
            @RequestParam(name = "commission", required = false) String commission,
            @RequestParam(name = "year", required = false) String year,
            @RequestParam(name = "paymentStatus", required = false) String paymentStatus,
            @RequestParam(name = "bourse", required = false) String bourse,
            @RequestParam(name = "registrationStatus", required = false) RegistrationStatus registrationStatus,
            @RequestParam(name = "sessionIdForRegistration", required = false) String sessionIdForRegistration,
            @RequestParam(name = "registrationType", required = false) TypeInscription registrationType,
            @RequestParam(name = "sortColumn", required = false) String sortColumn,
            @RequestParam(name = "sortDirection", required = false) boolean sortDirection

    ) {
        RequestPageableVO requestPageableVO = new RequestPageableVO(page, rpp);
        FilterDTO filters = FilterDTO
                .builder()
                .bourse(club)
                .year(year)
                .commission(commission)
                .bourse(bourse)
                .statusPayment(paymentStatus)
                .registrationStatus(registrationStatus)
                .registration(sessionIdForRegistration)
                .registrationType(registrationType)
                .build();
        return this.memberService.searchMember(requestPageableVO, criteria, value, filters, sortColumn, sortDirection);
    }

    //TODO refactoring check if we can find this last page
    @GetMapping(path = "print")
    ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> searchMemberToPrint(
            @RequestParam(name = "criteria", required = false) String criteria,
            @RequestParam(name = "value", required = false) String value,
            @RequestParam(name = "club", required = false) String club,
            @RequestParam(name = "commission", required = false) String commission,
            @RequestParam(name = "year", required = false) String year,
            @RequestParam(name = "bourse", required = false) String bourse,
            @RequestParam(name = "paymentStatus", required = false) String paymentStatus,
            @RequestParam(name = "registrationStatus", required = false) RegistrationStatus registrationStatus,
            @RequestParam(name = "sortColumn", required = false) String sortColumn
    ) {
        FilterDTO filters = FilterDTO
                .builder()
                .bourse(club)
                .year(year)
                .commission(commission)
                .bourse(bourse)
                .statusPayment(paymentStatus)
                .registrationStatus(registrationStatus)
                .build();
        return this.memberService.searchMemberToPrint(criteria, value, filters);
    }
}