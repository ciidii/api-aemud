package org.aemudapi.member.service;

import org.aemudapi.member.dtos.AddressInfoRequestDto;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AddressInfoService {
    ResponseEntity<ResponseVO<Void>> createAddressInfo(AddressInfoRequestDto requestDto);

    ResponseEntity<ResponseVO<AddressInfoRequestDto>> getCurrentSessionMemberAddress(String memberID);
}
