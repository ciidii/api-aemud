package com.amud.io.aemudapi.services;

import com.amud.io.aemudapi.dto.AcademicInfoRequestDTO;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AcademicInfoService {
    ResponseEntity<ResponseVO<Void>> createAcademicInfo(AcademicInfoRequestDTO academicInfoRequestDTO);

    ResponseEntity<ResponseVO<AcademicInfoRequestDTO>> getCurrentSessionMemberAddress(Long memberID);
}
