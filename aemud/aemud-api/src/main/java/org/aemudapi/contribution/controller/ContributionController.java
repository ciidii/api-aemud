package org.aemudapi.contribution.controller;

import lombok.AllArgsConstructor;
import org.aemudapi.contribution.dto.*;
import org.aemudapi.contribution.service.ContributionService;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contribution")
@AllArgsConstructor
public class ContributionController {
    private final ContributionService contributionService;

    @PostMapping("contribute")
    public ResponseEntity<ResponseVO<ContributionResponseDTO>> contribute(@RequestBody ContributionRequestDTO contributionDTO) {
        return this.contributionService.addContribute(contributionDTO);
    }

    @PostMapping("pay-contributions")
    ResponseEntity<ResponseVO<ContributionsPayementResponse>> payContributions(@RequestBody ContributionsPayementRequest payementRequest) {
        return this.contributionService.payContributions(payementRequest);
    }

    @PutMapping
    public ResponseEntity<ResponseVO<ContributionResponseDTO>> modifyContribution(ContributionRequestDTO contributionDTO) {
        return this.contributionService.modifyContribution(contributionDTO);
    }

    @DeleteMapping
    public ResponseEntity<ResponseVO<Void>> deleteContribution(String contributionId) {
        return this.contributionService.deleteContribution(contributionId);
    }

    @GetMapping("count-all-contribution")
    public ResponseEntity<ResponseVO<Integer>> countAllContributions() {
        return this.contributionService.countAllContributions();
    }

    @GetMapping("count-contribution-peer-year")
    public ResponseEntity<ResponseVO<Integer>> countContributionPeerMandat(@RequestParam("mandatId") String mandatId) {
        return this.contributionService.countContributionPeerMandat(mandatId);
    }

    @GetMapping("list-member-contribution-peer-month")
    public ResponseEntity<ResponseVO<List<ContributionResponseDTO>>> getContributionPeerMonth(@RequestParam("mandatId") String mandatId, @RequestParam("monthId") String monthId) {
        return this.contributionService.getContributionPeerMonth(mandatId, monthId);
    }

    @GetMapping("member-contributions")
    public ResponseEntity<ResponseVO<List<ContributionResponseDTO>>> getMemberContributions(@RequestParam("memberId") String memberId, @RequestParam("mandatId") String mandatId) {
        return this.contributionService.getMemberContributions(memberId, mandatId);
    }

    @PostMapping("create-membre-calendar")
    public ResponseEntity<ResponseVO<Void>> createContributionCalendar(@RequestBody MembreCalendarDTO membreCalendarDTO) {
        this.contributionService.createMemberCalendar(membreCalendarDTO.memberID(), membreCalendarDTO.mandatID());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("member-contribution-calendar")
    public ResponseEntity<ResponseVO<List<ContributionResponseDTO>>> getMemberContributionCalendar(@RequestParam("memberId") String memberId, @RequestParam("sessionId") String sessionId) {
        return this.contributionService.getMemberContributionsCalendar(memberId, sessionId);
    }
}
