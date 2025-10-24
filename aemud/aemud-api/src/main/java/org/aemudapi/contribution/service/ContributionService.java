package org.aemudapi.contribution.service;

import org.aemudapi.contribution.dto.*;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContributionService {
    public ResponseEntity<ResponseVO<ContributionResponseDTO>> addContribute(ContributionRequestDTO contributionDTO);

    public ResponseEntity<ResponseVO<ContributionResponseDTO>> modifyContribution(ContributionRequestDTO contributionDTO);

    public ResponseEntity<ResponseVO<Void>> deleteContribution(String contributionDTO);

    ResponseEntity<ResponseVO<Integer>> countAllContributions();

    ResponseEntity<ResponseVO<Integer>> countContributionPeerMandat(String mandatId);

    ResponseEntity<ResponseVO<List<ContributionResponseDTO>>> getContributionPeerMonth(String mandatId, String monthId);

    ResponseEntity<ResponseVO<List<ContributionResponseDTO>>> getMemberContributions(String memberId, String mandatId);

    ResponseEntity<ResponseVO<ContributionsPayementResponse>> payContributions(ContributionsPayementRequest payementRequest);

    public void createMemberCalendar(String memberId, String phaseId);

    ResponseEntity<ResponseVO<List<ContributionResponseDTO>>> getMemberContributionsCalendar(String memberId, String mandatId);
}
