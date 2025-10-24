package org.aemudapi.member.service.impl;


import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.contribution.service.ContributionService;
import org.aemudapi.exceptions.customeExceptions.MemberAllReadyRegisterException;
import org.aemudapi.member.dtos.MemberDataResponseDTO;
import org.aemudapi.member.dtos.RegistrationRequestDto;
import org.aemudapi.member.dtos.RegistrationRequestWithPhoneNumberDto;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.entity.Registration;
import org.aemudapi.member.entity.RegistrationStatus;
import org.aemudapi.member.entity.TypeInscription;
import org.aemudapi.member.mapper.MemberMapper;
import org.aemudapi.member.mapper.RegistrationMapper;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.member.repository.RegistrationRepository;
import org.aemudapi.member.service.RegistrationService;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationMapper registrationMapper;
    private final RegistrationRepository registrationRepository;
    private final MemberMapper memberMapper;
    private MemberRepository memberRepository;
    private ContributionService contributionService;

    @Override
    @Transactional
    public ResponseEntity<ResponseVO<Void>> registerMember(RegistrationRequestDto registrationRequestDto, String phaseId) {
        Optional<Member> member = this.registrationRepository.findMemberRegisteredMemberForMandat(registrationRequestDto.getMandatId(), registrationRequestDto.getMember());
        if (member.isPresent()) {
            throw new MemberAllReadyRegisterException("Member Already Registered");
        }

        if (registrationRequestDto.isStatusPayment()) {
            registrationRequestDto.setRegistrationStatus(RegistrationStatus.COMPLETED);
        } else {
            registrationRequestDto.setRegistrationStatus(RegistrationStatus.UNCOMPLETED);
        }
        Registration registration = this.registrationMapper.toEntity(registrationRequestDto);

        this.registrationRepository.save(registration);
        this.contributionService.createMemberCalendar(registrationRequestDto.getMember(), phaseId);
        ResponseVO<Void> responseVO = new ResponseVOBuilder<Void>().success().build();
        return new ResponseEntity<>(responseVO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> registerMemberWithNumberPhone(RegistrationRequestWithPhoneNumberDto registrationRequestDto) {
        Member member = this.memberRepository.findByNumberPhone(registrationRequestDto.getMemberPhoneNumber()).orElseThrow(() -> new EntityNotFoundException("Member Not Found"));
        Optional<Member> memberFromDB = this.registrationRepository.findMemberRegisteredMemberForMandat(registrationRequestDto.getMandatId(), member.getId());
        if (memberFromDB.isPresent()) {
            throw new MemberAllReadyRegisterException("Member Already Registered");
        }
        Registration registration = this.registrationMapper.toEntity(registrationRequestDto, member);
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
    public ResponseEntity<ResponseVO<Integer>> getRegistrationCountByMandat(String mandatId) {
        int registrationCount = this.registrationRepository.getRegistrationCountByMandat(mandatId);
        ResponseVO<Integer> responseVO = new ResponseVOBuilder<Integer>().addData(registrationCount).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Integer>> getPayedOrNoPayedSessionCountPeerMandat(String mandatId, Boolean statusPayment) {
        int num = this.registrationRepository.getPayedOrNoPayedSessionCountPeerMandat(mandatId, statusPayment);
        return new ResponseEntity<>(new ResponseVOBuilder<Integer>().addData(num).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Integer>> getNewOrRenewalAdherentForAMandat(String mandatId, TypeInscription typeInscription) {
        int num = this.registrationRepository.getNewOrRenewalAdherentForAMandat(mandatId, typeInscription);
        return new ResponseEntity<>(new ResponseVOBuilder<Integer>().addData(num).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> getMemberByMandat(String mandatId) {
        List<Member> members = this.registrationRepository.getMembersByMandat(mandatId);
        ResponseVO<List<MemberDataResponseDTO>> responseVO = new ResponseVOBuilder<List<MemberDataResponseDTO>>().addData(this.memberMapper.toDto(members)).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> getPayedOrNoPayedMembersPeerMandat(String mandatId, Boolean statusPayment) {
        List<Member> members = this.registrationRepository.getPayedOrNoPayedMembersPeerMandat(mandatId, statusPayment);
        ResponseVO<List<MemberDataResponseDTO>> responseVO = new ResponseVOBuilder<List<MemberDataResponseDTO>>().addData(this.memberMapper.toDto(members)).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> getMembersRegistrationsStatusForMandats(String mandatId, RegistrationStatus registrationStatus) {
        List<Member> members = this.registrationRepository.getMembersRegistrationsStatusForMandats(mandatId, registrationStatus);
        ResponseVO<List<MemberDataResponseDTO>> responseVO = new ResponseVOBuilder<List<MemberDataResponseDTO>>().addData(this.memberMapper.toDto(members)).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }
}
