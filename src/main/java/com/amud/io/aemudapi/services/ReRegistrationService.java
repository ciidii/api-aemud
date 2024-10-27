package com.amud.io.aemudapi.services;

import com.amud.io.aemudapi.dto.ReRegistrationDto;
import com.amud.io.aemudapi.dto.RegistrationPearYearDto;
import com.amud.io.aemudapi.dto.RegistrationResponseDto;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;

public interface ReRegistrationService {
    public ResponseEntity<ResponseVO<RegistrationResponseDto>> reRegisterMember(ReRegistrationDto reRegistrationKeyDto);
    public ResponseEntity<ResponseVO<RegistrationPearYearDto>> getAllReRegisterForAYear(Long year);
    public ResponseEntity<ResponseVO<RegistrationPearYearDto>> getAllNotReRegisterForAYear(Long year);
}
