package com.amud.io.aemudapi.services;

import com.amud.io.aemudapi.dto.ContributionKeyDto;
import com.amud.io.aemudapi.dto.ContributionResponseDto;
import com.amud.io.aemudapi.projection.MemberThatContributeDto;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContributionService {
    public ResponseEntity<ResponseVO<ContributionResponseDto>> contribute(ContributionKeyDto contributionKeyDto);
    public ResponseEntity<ResponseVO<List<MemberThatContributeDto>>> contributeForAMonthOfAYear(Long idYear , Long idMonth);
    public ResponseEntity<ResponseVO<List<MemberThatContributeDto>>> notContributeForAMonthOfAYear(Long idYear , Long idMonth);
}
