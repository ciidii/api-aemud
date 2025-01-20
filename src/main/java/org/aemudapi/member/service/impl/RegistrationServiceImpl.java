package org.aemudapi.member.service.impl;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.MemberDataResponseDTO;
import org.aemudapi.member.dtos.RegistrationRequestDto;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.entity.Registration;
import org.aemudapi.member.entity.RegistrationStatus;
import org.aemudapi.member.mapper.MemberMapper;
import org.aemudapi.member.mapper.RegistrationMapper;
import org.aemudapi.member.repository.RegistrationRepository;
import org.aemudapi.member.service.RegistrationService;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationMapper registrationMapper;
    private final RegistrationRepository registrationRepository;
    private final MemberMapper memberMapper;

    @Override
    public ResponseEntity<ResponseVO<Void>> registerMember(RegistrationRequestDto registrationRequestDto) {
        Registration registration = this.registrationMapper.toEntity(registrationRequestDto);
        this.registrationRepository.save(registration);
        ResponseVO<Void> responseVO = new ResponseVOBuilder<Void>().success().build();
        return new ResponseEntity<>(responseVO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> deleteRegistration(String id) {
        if (!this.registrationRepository.existsById(id)) {
            this.registrationRepository.deleteById(id);
            ResponseVO<Void> responseVO = new ResponseVOBuilder<Void>().success().build();
            return new ResponseEntity<>(responseVO, HttpStatus.OK);
        }
        throw new EntityNotFoundException("Registration with id " + id + " not found");
    }

    @Override
    public ResponseEntity<ResponseVO<Integer>> getRegistrationCountBySession(String session) {
        int registrationCount = this.registrationRepository.getRegistrationCountBySession(session);
        ResponseVO<Integer> responseVO = new ResponseVOBuilder<Integer>().addData(registrationCount).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Integer>> getPayedOrNoPayedSessionCountPeerSession(String session, Boolean statusPayment) {
        int num = this.registrationRepository.getPayedOrNoPayedSessionCountPeerSession(session, statusPayment);
        return new ResponseEntity<>(new ResponseVOBuilder<Integer>().addData(num).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> getMemberBySession(String sessionId) {
        List<Member> members = this.registrationRepository.getMembersBySession(sessionId);
        ResponseVO<List<MemberDataResponseDTO>> responseVO = new ResponseVOBuilder<List<MemberDataResponseDTO>>().addData(this.memberMapper.toDto(members)).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> getPayedOrNoPayedMembersPeerSession(String sessionId, Boolean statusPayment) {
        List<Member> members = this.registrationRepository.getPayedOrNoPayedMembersPeerSession(sessionId, statusPayment);
        ResponseVO<List<MemberDataResponseDTO>> responseVO = new ResponseVOBuilder<List<MemberDataResponseDTO>>().addData(this.memberMapper.toDto(members)).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> getMembersRegistrationsStatusForSessions(String sessionId, RegistrationStatus registrationStatus) {
        List<Member> members = this.registrationRepository.getMembersRegistrationsStatusForSessions(sessionId, registrationStatus);
        ResponseVO<List<MemberDataResponseDTO>> responseVO = new ResponseVOBuilder<List<MemberDataResponseDTO>>().addData(this.memberMapper.toDto(members)).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }
}
