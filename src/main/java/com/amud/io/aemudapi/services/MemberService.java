package com.amud.io.aemudapi.services;

import com.amud.io.aemudapi.dto.FilterDTO;
import com.amud.io.aemudapi.dto.MemberDataResponseDTO;
import com.amud.io.aemudapi.dto.MemberRequestDto;
import com.amud.io.aemudapi.dto.MemberResponseDto;
import com.amud.io.aemudapi.entities.Member;
import com.amud.io.aemudapi.utils.RequestPageableVO;
import com.amud.io.aemudapi.utils.ResponsePageableVO;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public interface MemberService {
    ResponseEntity<ResponseVO<MemberRequestDto>> addMember(MemberRequestDto memberRequestDto);

    ResponseEntity<ResponseVO<Void>> removeMember(Long id);

    ResponseEntity<ResponseVO<MemberRequestDto>> updateMember(MemberRequestDto memberRequestDto) throws GeneralSecurityException, IOException;

    ResponseEntity<ResponsePageableVO<MemberDataResponseDTO>> getAllMembers(RequestPageableVO requestPageableVO);

    ResponseEntity<ResponseVO<MemberDataResponseDTO>> getMemberById(Long id);

    ResponseEntity<ResponsePageableVO<MemberDataResponseDTO>> searchMember(RequestPageableVO requestPageableVO, String criteria, String value, FilterDTO filterDTO);

    ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> searchMemberToPrint(String criteria, String value, FilterDTO filters);
}