package org.aemudapi.contribution.service;

import org.aemudapi.contribution.dto.ContributionDTO;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContributionService {
    public ResponseEntity<ResponseVO<ContributionDTO>> contribute(ContributionDTO contributionDTO);

    public ResponseEntity<ResponseVO<ContributionDTO>> modifyContribution(ContributionDTO contributionDTO);

    public ResponseEntity<ResponseVO<Void>> deleteContribution(String contributionDTO);

    ResponseEntity<ResponseVO<Integer>> countAllContributions();

    ResponseEntity<ResponseVO<Integer>> countContributionPeerSession(String sessionId);

    ResponseEntity<ResponseVO<List<ContributionDTO>>> getContributionPeerMonth(String sessionId, String monthId);

    ResponseEntity<ResponseVO<List<ContributionDTO>>> getMemberContributions(String memberId, String sessionId);

    ResponseEntity<ResponseVO<Double>> getContributionsAmountPeerMonth(String monthId, String sessionId);

    ResponseEntity<ResponseVO<Double>> getContributionsAmountPeerSessions(String sessionId);

    ResponseEntity<ResponseVO<ContributionDTO>> contributeUsingNumPhone(String phoneNumber, String yearId, String monthId);
}
