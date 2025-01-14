package org.aemudapi.member.service;

import org.aemudapi.member.dtos.ContactInfoRequestDto;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ContactInfoService {
    ResponseEntity<ResponseVO<Void>> createContactInfo(ContactInfoRequestDto contactInfoRequestDto);

    ResponseEntity<ResponseVO<ContactInfoRequestDto>> getCurrentSessionMemberInfos(Long memberID);
}
