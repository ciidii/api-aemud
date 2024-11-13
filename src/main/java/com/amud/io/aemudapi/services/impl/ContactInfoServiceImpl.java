package com.amud.io.aemudapi.services.impl;

import com.amud.io.aemudapi.dto.ContactInfoRequestDto;
import com.amud.io.aemudapi.entities.ContactInfo;
import com.amud.io.aemudapi.entities.MemberAndYearKey;
import com.amud.io.aemudapi.entities.PersonToCall;
import com.amud.io.aemudapi.entities.YearOfSession;
import com.amud.io.aemudapi.mappers.ContactInfoMapper;
import com.amud.io.aemudapi.repositories.ContactInfoRepository;
import com.amud.io.aemudapi.repositories.PersonToCallRepository;
import com.amud.io.aemudapi.repositories.YearOfSessionRepository;
import com.amud.io.aemudapi.services.ContactInfoService;
import com.amud.io.aemudapi.utils.ResponseVO;
import com.amud.io.aemudapi.utils.ResponseVOBuilder;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactInfoServiceImpl implements ContactInfoService {
    private final ContactInfoMapper contactInfoMapper;
    private final ContactInfoRepository contactInfoRepository;
    private final PersonToCallRepository personToCallRepository;
    private final YearOfSessionRepository yearOfSessionRepository;


    @Override
    public ResponseEntity<ResponseVO<Void>> createContactInfo(ContactInfoRequestDto contactInfoRequestDto) {
        ContactInfo contactInfo;
        contactInfo = this.contactInfoMapper.toEntity(contactInfoRequestDto);
        List<PersonToCall> personToCalls = contactInfo.getPersonToCalls();
        personToCalls = this.personToCallRepository.saveAll(personToCalls);
        contactInfo.setPersonToCalls(personToCalls);
        this.contactInfoRepository.save(contactInfo);
        ResponseVO<Void> responseVO = new ResponseVOBuilder<Void>().success().build();
        return new ResponseEntity<>(responseVO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseVO<ContactInfoRequestDto>> getCurrentSessionMemberInfos(Long memberID) {
        YearOfSession yearOfSession = this.yearOfSessionRepository.findCurrentSession();
        MemberAndYearKey memberAndYearKey = new MemberAndYearKey(yearOfSession.getIdYear(), memberID);
        ContactInfo addressInfo = this.contactInfoRepository.findById(memberAndYearKey).orElseThrow(() -> new EntityNotFoundException("C'est utilisateur ne s'Inscrit cette année"));
        ContactInfoRequestDto infoRequestDto = this.contactInfoMapper.toDTO(addressInfo);
        ResponseVO<ContactInfoRequestDto> responseVO = new ResponseVOBuilder<ContactInfoRequestDto>().addData(infoRequestDto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }
}
