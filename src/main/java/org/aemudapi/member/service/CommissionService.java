package org.aemudapi.member.service;

import org.aemudapi.member.dtos.CommissionDto;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommissionService {
    ResponseEntity<ResponseVO<CommissionDto>> addCommission(CommissionDto commissionDto);

    ResponseEntity<ResponseVO<List<CommissionDto>>> getAllCommission();

    ResponseEntity<ResponseVO<Void>> deleteCommission(Long commissionID);

    ResponseEntity<ResponseVO<CommissionDto>> getSingleCommission(Long commissionID);

    ResponseEntity<ResponseVO<CommissionDto>> updateCommission(CommissionDto commission);
}
