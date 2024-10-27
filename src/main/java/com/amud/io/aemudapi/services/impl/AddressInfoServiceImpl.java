package com.amud.io.aemudapi.services.impl;

import com.amud.io.aemudapi.dto.AddressInfoRequestDto;
import com.amud.io.aemudapi.entities.AddressInfo;
import com.amud.io.aemudapi.entities.MemberAndYearKey;
import com.amud.io.aemudapi.entities.YearOfSession;
import com.amud.io.aemudapi.mappers.AddressInfoMapper;
import com.amud.io.aemudapi.repositories.AddressInfoRepository;
import com.amud.io.aemudapi.repositories.YearOfSessionRepository;
import com.amud.io.aemudapi.services.AddressInfoService;
import com.amud.io.aemudapi.utils.ResponseVO;
import com.amud.io.aemudapi.utils.ResponseVOBuilder;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;

@Service
@AllArgsConstructor
public class AddressInfoServiceImpl implements AddressInfoService {
    private AddressInfoMapper addressInfoMapper;
    private AddressInfoRepository addressInfoRepository;
    private YearOfSessionRepository yearOfSessionRepository;
    @Override
    public ResponseEntity<ResponseVO<Void>> createAddressInfo(AddressInfoRequestDto requestDto) {
        AddressInfo addressInfo;
        addressInfo = this.addressInfoMapper.toEntity(requestDto);
        this.addressInfoRepository.save(addressInfo);
        ResponseVO<Void> responseVO = new ResponseVOBuilder<Void>().success().build();
        return new ResponseEntity<>(responseVO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseVO<AddressInfoRequestDto>> getCurrentSessionMemberAddress(Long memberID) {
        YearOfSession yearOfSession = this.yearOfSessionRepository.findCurrentSession();
        MemberAndYearKey memberAndYearKey = new MemberAndYearKey(yearOfSession.getIdYear(), memberID);
        AddressInfo addressInfo = this.addressInfoRepository.findById(memberAndYearKey).orElseThrow(()->new EntityNotFoundException("C'est utilisateur ne s'Inscrit cette année"));
        AddressInfoRequestDto infoRequestDto = this.addressInfoMapper.toDto(addressInfo);
        ResponseVO<AddressInfoRequestDto> responseVO = new ResponseVOBuilder<AddressInfoRequestDto>().addData(infoRequestDto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }
}
