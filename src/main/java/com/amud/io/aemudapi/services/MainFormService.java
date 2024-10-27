package com.amud.io.aemudapi.services;

import com.amud.io.aemudapi.dto.MemberDataRequestDTO;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MainFormService {
    ResponseEntity<ResponseVO<Void>> registerMember(MemberDataRequestDTO memberDataRequestDTO);
}
