package com.amud.io.aemudapi.services.impl;

import com.amud.io.aemudapi.Specifications.MemberSpecification;
import com.amud.io.aemudapi.dto.*;
import com.amud.io.aemudapi.entities.LogMemberBackup;
import com.amud.io.aemudapi.entities.Member;
import com.amud.io.aemudapi.mappers.MemberMapper;
import com.amud.io.aemudapi.mappers.MemberResponseMapper;
import com.amud.io.aemudapi.repositories.LogMemberBackupRepository;
import com.amud.io.aemudapi.repositories.MemberRepository;
import com.amud.io.aemudapi.services.AcademicInfoService;
import com.amud.io.aemudapi.services.AddressInfoService;
import com.amud.io.aemudapi.services.ContactInfoService;
import com.amud.io.aemudapi.services.MemberService;
import com.amud.io.aemudapi.utils.RequestPageableVO;
import com.amud.io.aemudapi.utils.ResponsePageableVO;
import com.amud.io.aemudapi.utils.ResponseVO;
import com.amud.io.aemudapi.utils.ResponseVOBuilder;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.amud.io.aemudapi.utils.Utils.makeFilterCriteriaSpec;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final LogMemberBackupRepository logMemberBackupRepository;
    private final MemberResponseMapper memberResponseMapper;
    private final AddressInfoService addressInfoService;
    private final ContactInfoService contactInfoService;
    private final AcademicInfoService academicInfoService;

    @Override
    @Transactional
    public ResponseEntity<ResponseVO<MemberRequestDto>> addMember(MemberRequestDto memberRequestDto) {
        Member member = this.memberMapper.toEntity(memberRequestDto);
        Member memberFromDB = this.memberRepository.save(member);
        MemberRequestDto dto = this.memberMapper.toDto(memberFromDB);
        ResponseVO<MemberRequestDto> responseVO = new ResponseVOBuilder<MemberRequestDto>().addData(dto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseVO<MemberRequestDto>> updateMember(MemberRequestDto memberRequestDto) {
        this.memberRepository.save(memberMapper.toEntity(memberRequestDto));
        ResponseVO<MemberRequestDto> responseVO = new ResponseVOBuilder<MemberRequestDto>().addData(memberRequestDto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ResponsePageableVO<MemberDataResponseDTO>> getAllMembers(RequestPageableVO requestPageableVO) {
        PageRequest pageRequest = PageRequest.of(requestPageableVO.getPage() - 1, requestPageableVO.getRpp());
        Page<Member> memberPage = memberRepository.findAll(pageRequest);
        ResponsePageableVO<MemberDataResponseDTO> responsePageableVO = new ResponsePageableVO<>(memberPage.getTotalElements(), fetchPageToMemberResponseDTO(memberPage), requestPageableVO);

        return new ResponseEntity<>(responsePageableVO, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ResponseVO<MemberDataResponseDTO>> getMemberById(Long id) {
        AcademicInfoRequestDTO academicInfo = this.academicInfoService.getCurrentSessionMemberAcademicInfo(id).getBody().getData();
        AddressInfoRequestDto addressInfo = this.addressInfoService.getCurrentSessionMemberAddress(id).getBody().getData();
        ContactInfoRequestDto contactInfo = this.contactInfoService.getCurrentSessionMemberInfos(id).getBody().getData();
        return memberRepository.findById(id).map(member -> {
            MemberDataResponseDTO memberRequestDto = memberMapper.memberDataDto(member);
            memberRequestDto.setAcademicInfo(academicInfo);
            memberRequestDto.setAddressInfo(addressInfo);
            memberRequestDto.setContactInfo(contactInfo);
            ResponseVO<MemberDataResponseDTO> responseVO = new ResponseVOBuilder<MemberDataResponseDTO>().addData(memberRequestDto).build();
            return ResponseEntity.status(HttpStatus.OK).body(responseVO);
        }).orElseThrow(() -> new EntityNotFoundException("Un membre avec l'id " + id + " n'existe pas"));
    }

    @Override
    public ResponseEntity<ResponsePageableVO<MemberDataResponseDTO>> searchMember(RequestPageableVO requestPageableVO, String criteria, String value, FilterDTO filterDTO) {
        PageRequest pageRequest = PageRequest.of(requestPageableVO.getPage() - 1, requestPageableVO.getRpp());
        Specification<Member> memberSpecification = makeFilterCriteriaSpec(criteria, value, filterDTO);


        Page<Member> memberPage = memberRepository.findAll(memberSpecification, pageRequest);
        List<MemberDataResponseDTO> memberDataResponseDTO = this.fetchPageToMemberResponseDTO(memberPage);
        ResponsePageableVO<MemberDataResponseDTO> responsePageableVO = new ResponsePageableVO<>(memberPage.getTotalElements(), memberDataResponseDTO, requestPageableVO);
        return new ResponseEntity<>(responsePageableVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> searchMemberToPrint(String criteria, String value, FilterDTO filterDTO) {
        Specification<Member> memberSpecification = makeFilterCriteriaSpec(criteria, value, filterDTO);
        List<Member> members = memberRepository.findAll(memberSpecification);
        List<MemberDataResponseDTO> responseDTOS = this.fetchPageToMemberResponseDTO(members);
        ResponseVO<List<MemberDataResponseDTO>> listResponseVO = new ResponseVOBuilder<List<MemberDataResponseDTO>>().addData(responseDTOS).build();
        return new ResponseEntity<>(listResponseVO, HttpStatus.OK);
    }


    @Override
    public ResponseEntity removeMember(Long id) {
        Member member = this.memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Un membre avec l'id " + id + " n'existe pas"));
        List<LogMemberBackup> logMemberBackups = this.logMemberBackupRepository.findAllByMemberId(member);
        this.logMemberBackupRepository.deleteAll(logMemberBackups);
        this.memberRepository.delete(member);
        ResponseVO responseVO = new ResponseVOBuilder<Void>().success().build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    private <T extends Iterable<Member>> List<MemberDataResponseDTO> fetchPageToMemberResponseDTO(T memberPage) {
        List<MemberDataResponseDTO> memberResponseDtos = new ArrayList<>();
        AddressInfoRequestDto addressInfo;
        ContactInfoRequestDto contactInfoRequestDto;
        for (Member member : memberPage) {
            MemberDataResponseDTO memberDataResponseDTO = new MemberDataResponseDTO();

            addressInfo = Objects.requireNonNull(this.addressInfoService.getCurrentSessionMemberAddress(member.getId()).getBody()).getData();
            contactInfoRequestDto = this.contactInfoService.getCurrentSessionMemberInfos(member.getId()).getBody().getData();
            memberDataResponseDTO.setMember(this.memberResponseMapper.toDto(member));
            memberDataResponseDTO.setContactInfo(contactInfoRequestDto);
            memberDataResponseDTO.setAddressInfo(addressInfo);
            memberResponseDtos.add(memberDataResponseDTO);
        }
        return memberResponseDtos;
    }
}
