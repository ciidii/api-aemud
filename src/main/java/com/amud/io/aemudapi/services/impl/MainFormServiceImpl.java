package com.amud.io.aemudapi.services.impl;

import com.amud.io.aemudapi.dto.*;
import com.amud.io.aemudapi.exceptions.customeExceptions.MemberNotCreatedException;
import com.amud.io.aemudapi.services.*;
import com.amud.io.aemudapi.utils.ResponseVO;
import com.amud.io.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MainFormServiceImpl implements MainFormService {
    private final MemberService memberService;
    private final YearOfSessionServices yearOfSessionServices;
    private final AcademicInfoService academicInfoService;
    private final AddressInfoService addressInfoService;
    private final ContactInfoService contactInfoService;

    public MainFormServiceImpl(MemberService memberService, YearOfSessionServices yearOfSessionServices, AcademicInfoService academicInfoService, AddressInfoService addressInfoService, ContactInfoService contactInfoService) {
        this.memberService = memberService;
        this.yearOfSessionServices = yearOfSessionServices;
        this.academicInfoService = academicInfoService;

        this.addressInfoService = addressInfoService;
        this.contactInfoService = contactInfoService;
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> registerMember(MemberDataRequestDTO memberDataRequestDTO) {
        MemberRequestDto memberRequestDto = memberDataRequestDTO.getMember();
        Long currentSession = this.yearOfSessionServices.getCurrentSession().getBody().getData().getId();
        memberRequestDto.getMembershipInfo().setYearOfMembership(currentSession);
        ResponseEntity<ResponseVO<MemberRequestDto>> response = this.memberService.addMember(memberRequestDto, true);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            if (response.getBody().getResult().equals("Succeeded")) {
                Long memberID = response.getBody().getData().getId();
                AcademicInfoRequestDTO academicInfoRequestDTO = memberDataRequestDTO.getAcademicInfo();
                academicInfoRequestDTO.setMemberID(memberID);
                academicInfoRequestDTO.setIdYear(currentSession);
                this.academicInfoService.createAcademicInfo(academicInfoRequestDTO);

                AddressInfoRequestDto addressInfoRequestDto = memberDataRequestDTO.getAddressInfo();
                addressInfoRequestDto.setMemberID(memberID);
                addressInfoRequestDto.setIdYear(currentSession);
                this.addressInfoService.createAddressInfo(addressInfoRequestDto);

                ContactInfoRequestDto contactInfoRequestDto = memberDataRequestDTO.getContactInfo();
                contactInfoRequestDto.setMemberID(memberID);
                contactInfoRequestDto.setIdYear(currentSession);
                this.contactInfoService.createContactInfo(contactInfoRequestDto);
                return new ResponseEntity<>(new ResponseVOBuilder<Void>().success().build(), HttpStatus.CREATED);
            }
            throw new MemberNotCreatedException("L'Utilisateur n'est pas créer premier ifs");
        }
        throw new MemberNotCreatedException("L'Utilisateur n'est pas créer");
    }

}
