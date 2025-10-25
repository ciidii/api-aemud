package org.aemudapi.member.service;

import org.aemudapi.member.dtos.FilterDTO;
import org.aemudapi.member.dtos.MemberDataResponseDTO;
import org.aemudapi.member.dtos.MemberRequestDto;
import org.aemudapi.utils.RequestPageableVO;
import org.aemudapi.utils.ResponsePageableVO;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {
    ResponseEntity<ResponseVO<MemberDataResponseDTO>> addMember(MemberRequestDto memberRequestDto);

    ResponseEntity<ResponseVO<Void>> removeMember(String id);

    ResponseEntity<ResponseVO<MemberDataResponseDTO>> updateMember(MemberRequestDto memberRequestDto);

    ResponseEntity<ResponseVO<MemberDataResponseDTO>> getMemberById(String id);

    ResponseEntity<ResponsePageableVO<MemberDataResponseDTO>> searchMember(RequestPageableVO requestPageableVO, String criteria, String value, FilterDTO filterDTO, String sortColumn, boolean sortDirection);

    ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> searchMemberToPrint(String criteria, String value, FilterDTO filters);

    ResponseEntity<ResponseVO<MemberDataResponseDTO>> findMemberByNumberPhone(String numberphone);
}