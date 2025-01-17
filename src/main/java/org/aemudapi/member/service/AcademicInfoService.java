package org.aemudapi.member.service;

import org.aemudapi.member.dtos.AcademicInfoRequestDTO;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AcademicInfoService {
    ResponseEntity<ResponseVO<Void>> createAcademicInfo(AcademicInfoRequestDTO academicInfoRequestDTO);

    ResponseEntity<ResponseVO<AcademicInfoRequestDTO>> getCurrentSessionMemberAcademicInfo(String memberID);
}
