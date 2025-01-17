package org.aemudapi.member.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.ContactInfoRequestDto;
import org.aemudapi.member.entity.Member_Session_PK;
import org.aemudapi.member.entity.PersonToCall;
import org.aemudapi.member.entity.Session;
import org.aemudapi.exceptions.customeExceptions.NoActiveSection;
import org.aemudapi.member.entity.ContactInfo;
import org.aemudapi.member.mapper.ContactInfoMapper;
import org.aemudapi.member.repository.ContactInfoRepository;
import org.aemudapi.member.repository.YearOfSessionRepository;
import org.aemudapi.member.service.ContactInfoService;
import org.aemudapi.member.repository.PersonToCallRepository;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
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
        Session session = this.yearOfSessionRepository.findCurrentSession().orElseThrow(() -> new NoActiveSection("No active session"));
        ContactInfo addressInfo = this.contactInfoRepository.findById(session.getId()).orElseThrow(() -> new EntityNotFoundException("C'est utilisateur ne s'Inscrit cette année"));
        ContactInfoRequestDto infoRequestDto = this.contactInfoMapper.toDTO(addressInfo);
        ResponseVO<ContactInfoRequestDto> responseVO = new ResponseVOBuilder<ContactInfoRequestDto>().addData(infoRequestDto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }
}
