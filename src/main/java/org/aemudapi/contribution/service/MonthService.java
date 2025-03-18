package org.aemudapi.contribution.service;

import org.aemudapi.contribution.dto.MonthDTO;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MonthService {
    public ResponseEntity<ResponseVO<Void>> addMonth(MonthDTO month);

    public ResponseEntity<ResponseVO<Void>> addMonth(List<MonthDTO> month);

    public ResponseEntity<ResponseVO<Void>> updateMonth(MonthDTO month);

    public ResponseEntity<ResponseVO<Void>> deleteMonth(MonthDTO month);

    ResponseEntity<ResponseVO<List<MonthDTO>>> getAllMonth();
}
