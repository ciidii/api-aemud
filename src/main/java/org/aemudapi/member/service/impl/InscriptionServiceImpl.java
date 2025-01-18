package org.aemudapi.member.service.impl;


import org.aemudapi.exceptions.customeExceptions.MemberNotCreatedException;
import org.aemudapi.member.dtos.*;
import org.aemudapi.member.service.*;
import org.aemudapi.member.service.SessionService;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InscriptionServiceImpl implements InscriptionService {
    private final MemberService memberService;
    private final SessionService session;
    private final AcademicInfoService academicInfoService;
    private final AddressInfoService addressInfoService;
    private final ContactInfoService contactInfoService;

    public InscriptionServiceImpl(MemberService memberService, SessionService session, AcademicInfoService academicInfoService, AddressInfoService addressInfoService, ContactInfoService contactInfoService) {
        this.memberService = memberService;
        this.session = session;
        this.academicInfoService = academicInfoService;

        this.addressInfoService = addressInfoService;
        this.contactInfoService = contactInfoService;
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseVO<Void>> registerMember(MemberDataRequestDTO memberDataRequestDTO) {
        MemberRequestDto memberRequestDto = memberDataRequestDTO.getMember();
        String currentSession = this.session.getCurrentSession().getBody().getData().getId();
        memberRequestDto.getMembershipInfo().setYearOfMembership(currentSession);
        ResponseEntity<ResponseVO<MemberRequestDto>> response = this.memberService.addMember(memberRequestDto);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            if (response.getBody().getResult().equals("Succeeded")) {
                String memberID = response.getBody().getData().getId();
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
