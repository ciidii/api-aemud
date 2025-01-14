package org.aemudapi.member.service;

import org.aemudapi.member.dtos.MemberDataRequestDTO;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface InscriptionService {
    ResponseEntity<ResponseVO<Void>> registerMember(MemberDataRequestDTO memberDataRequestDTO);
}
