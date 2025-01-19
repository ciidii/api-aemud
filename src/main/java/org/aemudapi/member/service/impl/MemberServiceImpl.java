package org.aemudapi.member.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.club.entity.Club;
import org.aemudapi.club.repository.ClubRepository;
import org.aemudapi.commission.entity.Commission;
import org.aemudapi.member.dtos.*;
import org.aemudapi.member.entity.Bourse;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.mapper.MemberMapper;
import org.aemudapi.member.mapper.MemberResponseMapper;
import org.aemudapi.member.repository.BourseRepository;
import org.aemudapi.member.repository.CommissionRepository;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.member.service.MemberService;
import org.aemudapi.utils.RequestPageableVO;
import org.aemudapi.utils.ResponsePageableVO;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.aemudapi.utils.Utils.makeFilterCriteriaSpec;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final MemberResponseMapper memberResponseMapper;
    private final ClubRepository clubRepository;
    private final CommissionRepository commissionRepository;
    private final BourseRepository bourseRepository;

    @Override
    public ResponseEntity<ResponseVO<MemberDataResponseDTO>> addMember(MemberRequestDto memberRequestDto) {
        Member member = this.memberMapper.toEntity(memberRequestDto);
        Member memberFromDB = this.memberRepository.save(member);
        MemberDataResponseDTO dto = this.memberMapper.toDto(memberFromDB);
        ResponseVO<MemberDataResponseDTO> responseVO = new ResponseVOBuilder<MemberDataResponseDTO>().addData(dto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseVO<MemberRequestDto>> updateMember(MemberRequestDto memberRequestDto) {
        this.memberRepository.save(memberMapper.toEntity(memberRequestDto));
        ResponseVO<MemberRequestDto> responseVO = new ResponseVOBuilder<MemberRequestDto>().addData(memberRequestDto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponsePageableVO<MemberDataResponseDTO>> getAllMembers(RequestPageableVO requestPageableVO) {
        PageRequest pageRequest = PageRequest.of(requestPageableVO.getPage() - 1, requestPageableVO.getRpp());
        Page<Member> memberPage = memberRepository.findAll(pageRequest);
        ResponsePageableVO<MemberDataResponseDTO> responsePageableVO = new ResponsePageableVO<>(memberPage.getTotalElements(), fetchPageToMemberResponseDTO(memberPage), requestPageableVO);

        return new ResponseEntity<>(responsePageableVO, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ResponseVO<MemberDataResponseDTO>> getMemberById(String id) {
        return memberRepository.findById(id).map(member -> {
            MemberDataResponseDTO memberRequestDto = memberMapper.memberDataDto(member);
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
    public ResponseEntity removeMember(String id) {
        Member member = this.memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Un membre avec l'id " + id + " n'existe pas"));
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
            memberResponseDtos.add(memberDataResponseDTO);
        }
        return memberResponseDtos;
    }
}
