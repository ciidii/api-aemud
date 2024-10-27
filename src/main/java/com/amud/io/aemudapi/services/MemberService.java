package com.amud.io.aemudapi.services;

import com.amud.io.aemudapi.dto.MemberDataResponseDTO;
import com.amud.io.aemudapi.dto.MemberRequestDto;
import com.amud.io.aemudapi.utils.RequestPageableVO;
import com.amud.io.aemudapi.utils.ResponsePageableVO;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
public interface MemberService {
    ResponseEntity<ResponseVO<MemberRequestDto>> addMember(MemberRequestDto memberRequestDto, boolean backup);

    ResponseEntity<ResponseVO<Void>> removeMember(Long id);

    ResponseEntity<ResponseVO<MemberRequestDto>> updateMember(MemberRequestDto memberRequestDto) throws GeneralSecurityException, IOException;

    ResponseEntity<ResponsePageableVO<MemberDataResponseDTO>> getAllMembers(RequestPageableVO requestPageableVO);

    ResponseEntity<ResponseVO<MemberDataResponseDTO>> getMemberById(Long id);
}