package org.aemudapi.contribution.controller;

import lombok.AllArgsConstructor;
import org.aemudapi.contribution.dto.ContributionDTO;
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

    @PutMapping
    public ResponseEntity<ResponseVO<ContributionDTO>> modifyContribution(ContributionDTO contributionDTO) {
        return this.contributionService.modifyContribution(contributionDTO);
    }

    @DeleteMapping
    public ResponseEntity<ResponseVO<Void>> deleteContribution(String contributionId) {
        return this.contributionService.deleteContribution(contributionId);
    }

    @GetMapping("all")
    public ResponseEntity<ResponseVO<Integer>> getAllContributions() {
        return this.contributionService.countAllContributions();
    }

    @GetMapping("contribution-year")
    public ResponseEntity<ResponseVO<Integer>> getContributionPeerSession(@RequestParam String sessionId) {
        return this.contributionService.countContributionPeerSession(sessionId);
    }

    @GetMapping("contribution-month")
    public ResponseEntity<ResponseVO<List<ContributionDTO>>> getContributionPeerMonth(@RequestParam String sessionId, @RequestParam String monthId) {
        return this.contributionService.getContributionPeerMonth(sessionId, monthId);
    }

    @GetMapping("contribution-member")
    public ResponseEntity<ResponseVO<List<ContributionDTO>>> getMemberContributions(@RequestParam String memberId, @RequestParam String sessionId) {
        return this.contributionService.getMemberContributions(memberId, sessionId);
    }

    @GetMapping("amount-peer-month")
    ResponseEntity<ResponseVO<Double>> getContributionsAmountPeerMonth(@RequestParam("monthId") String monthId, @RequestParam("sessionId") String sessionId) {
        return this.contributionService.getContributionsAmountPeerMonth(monthId, sessionId);
    }

    @GetMapping("amount-peer-session")
    ResponseEntity<ResponseVO<Double>> getContributionsAmountPeerMonth(@RequestParam("sessionId") String sessionId) {
        return this.contributionService.getContributionsAmountPeerSessions(sessionId);
    }
}
