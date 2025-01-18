package org.aemudapi.member.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.exceptions.customeExceptions.NoActiveSection;
import org.aemudapi.member.dtos.AddressInfoRequestDto;
import org.aemudapi.member.entity.AddressInfo;
import org.aemudapi.member.entity.Session;
import org.aemudapi.member.mapper.AddressInfoMapper;
import org.aemudapi.member.repository.AddressInfoRepository;
import org.aemudapi.member.repository.YearOfSessionRepository;
import org.aemudapi.member.service.AddressInfoService;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public ResponseEntity<ResponseVO<AddressInfoRequestDto>> getCurrentSessionMemberAddress(String memberID) {
        Session session = this.yearOfSessionRepository.findCurrentSession().orElseThrow(()->new NoActiveSection("No active session"));
        AddressInfo addressInfo = this.addressInfoRepository.findById(session.getId()).orElseThrow(() -> new EntityNotFoundException("C'est utilisateur ne s'Inscrit cette année"));
        AddressInfoRequestDto infoRequestDto = this.addressInfoMapper.toDto(addressInfo);
        ResponseVO<AddressInfoRequestDto> responseVO = new ResponseVOBuilder<AddressInfoRequestDto>().addData(infoRequestDto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }
}
