package com.amud.io.aemudapi.services;

import com.amud.io.aemudapi.dto.CommissionDto;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommissionService {
    public ResponseEntity<ResponseVO<CommissionDto>> addCommission(CommissionDto commissionDto);
    public ResponseEntity<ResponseVO<List<CommissionDto>>> getAllCommission();
}
