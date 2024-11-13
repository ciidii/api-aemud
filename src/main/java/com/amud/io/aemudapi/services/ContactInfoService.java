package com.amud.io.aemudapi.services;

import com.amud.io.aemudapi.dto.ContactInfoRequestDto;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ContactInfoService {
    ResponseEntity<ResponseVO<Void>> createContactInfo(ContactInfoRequestDto contactInfoRequestDto);

    ResponseEntity<ResponseVO<ContactInfoRequestDto>> getCurrentSessionMemberInfos(Long memberID);
}
