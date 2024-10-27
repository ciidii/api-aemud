package com.amud.io.aemudapi.services;

import com.amud.io.aemudapi.dto.AddressInfoRequestDto;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AddressInfoService {
    public ResponseEntity<ResponseVO<Void>> createAddressInfo(AddressInfoRequestDto requestDto);

    ResponseEntity<ResponseVO<AddressInfoRequestDto>> getCurrentSessionMemberAddress(Long memberID);
}
