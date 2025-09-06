package org.aemudapi.contribution.controller;

import lombok.AllArgsConstructor;
import org.aemudapi.contribution.dto.ContributionDTO;
import org.aemudapi.contribution.dto.ContributionWithPhoneNumberDTO;
import org.aemudapi.contribution.service.ContributionService;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contribution")
@AllArgsConstructor
public class ContributionController {
    private final ContributionService contributionService;

    @PostMapping("contribute")
    public ResponseEntity<ResponseVO<ContributionDTO>> contribute(@RequestBody ContributionDTO contributionDTO) {
        return this.contributionService.contribute(contributionDTO);
    }

//    @PostMapping("contribute-phone")
//    public ResponseEntity<ResponseVO<ContributionDTO>> contributeUsingNumberPhone(@RequestBody ContributionWithPhoneNumberDTO contributionDTO) {
//        return this.contributionService.contributeUsingNumPhone(contributionDTO.getPhoneNumber(), contributionDTO.getSessionId(), contributionDTO.getMonthId());
//    }

    @PutMapping
    public ResponseEntity<ResponseVO<ContributionDTO>> modifyContribution(ContributionDTO contributionDTO) {
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
    public ResponseEntity<ResponseVO<Integer>> countContributionPeerSession(@RequestParam("sessionId") String sessionId) {
        return this.contributionService.countContributionPeerSession(sessionId);
    }

    @GetMapping("list-member-contribution-peer-month")
    public ResponseEntity<ResponseVO<List<ContributionDTO>>> getContributionPeerMonth(@RequestParam("sessionId") String sessionId, @RequestParam("monthId") String monthId) {
        return this.contributionService.getContributionPeerMonth(sessionId, monthId);
    }

    @GetMapping("member-contributions")
    public ResponseEntity<ResponseVO<List<ContributionDTO>>> getMemberContributions(@RequestParam("memberId") String memberId, @RequestParam("sessionId") String sessionId) {
        return this.contributionService.getMemberContributions(memberId, sessionId);
    }
//
//    @GetMapping("amount-peer-month")
//    ResponseEntity<ResponseVO<Double>> getContributionsAmountPeerMonth(@RequestParam("monthId") String monthId, @RequestParam("sessionId") String sessionId) {
//        return this.contributionService.getContributionsAmountPeerMonth(monthId, sessionId);
//    }
//
//    @GetMapping("amount-peer-session")
//    ResponseEntity<ResponseVO<Double>> getContributionsAmountPeerMonth(@RequestParam("sessionId") String sessionId) {
//        return this.contributionService.getContributionsAmountPeerSessions(sessionId);
//    }
}
