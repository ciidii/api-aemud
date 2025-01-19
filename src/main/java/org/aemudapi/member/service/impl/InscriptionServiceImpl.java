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
public class InscriptionServiceImpl implements InscriptionService {
    private final MemberService memberService;
    private final SessionService session;

    public InscriptionServiceImpl(MemberService memberService, SessionService session) {
        this.memberService = memberService;
        this.session = session;
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> registerMember(MemberDataRequestDTO memberDataRequestDTO) {
        MemberRequestDto memberRequestDto = memberDataRequestDTO.getMember();
        String currentSession = this.session.getCurrentSession().getBody().getData().getId();
        ResponseEntity<ResponseVO<MemberDataResponseDTO>> response = this.memberService.addMember(memberRequestDto);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            if (response.getBody().getResult().equals("Succeeded")) {
                String memberID = response.getBody().getData().getId();
                AcademicInfoRequestDTO academicInfoRequestDTO = memberDataRequestDTO.getAcademicInfo();

                AddressInfoRequestDto addressInfoRequestDto = memberDataRequestDTO.getAddressInfo();

                ContactInfoRequestDto contactInfoRequestDto = memberDataRequestDTO.getContactInfo();
                return new ResponseEntity<>(new ResponseVOBuilder<Void>().success().build(), HttpStatus.CREATED);
            }
            throw new MemberNotCreatedException("L'Utilisateur n'est pas créer premier ifs");
        }
        throw new MemberNotCreatedException("L'Utilisateur n'est pas créer");
    }

}
