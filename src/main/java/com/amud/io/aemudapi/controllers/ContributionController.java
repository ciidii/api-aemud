package com.amud.io.aemudapi.controllers;

import com.amud.io.aemudapi.dto.ContributionKeyDto;
import com.amud.io.aemudapi.dto.ContributionResponseDto;
import com.amud.io.aemudapi.projection.MemberThatContributeDto;
import com.amud.io.aemudapi.services.ContributionService;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contribution")
public class ContributionController {
    private final ContributionService contributionService;

    public ContributionController(ContributionService contributionService) {
        this.contributionService = contributionService;
    }

    @PostMapping
    public ResponseEntity<ResponseVO<ContributionResponseDto>> contribute(@RequestBody ContributionKeyDto contributionKeyDto) {
        return this.contributionService.contribute(contributionKeyDto);
    }

    @GetMapping("all-for-Month")
    public ResponseEntity<ResponseVO<List<MemberThatContributeDto>>> findAllContributeForAMonthOfAYear(@RequestParam("idMonth") Long idMonth, @RequestParam("idYear") Long idYear) {
        return this.contributionService.contributeForAMonthOfAYear(idMonth, idYear);
    }

    @GetMapping("not-all-for-Month")
    public ResponseEntity<ResponseVO<List<MemberThatContributeDto>>> findAllNotContributeForAMonthOfAYear(@RequestParam("idMonth") Long idMonth, @RequestParam("idYear") Long idYear) {
        return this.contributionService.notContributeForAMonthOfAYear(idMonth, idYear);
    }
}
