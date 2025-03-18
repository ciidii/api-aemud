package org.aemudapi.member.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.FilterDTO;
import org.aemudapi.member.dtos.MemberDataResponseDTO;
import org.aemudapi.member.dtos.MemberRequestDto;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.mapper.MemberMapper;
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

import java.util.ArrayList;
import java.util.List;

import static org.aemudapi.utils.Utils.makeFilterCriteriaSpec;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public ResponseEntity<ResponseVO<MemberDataResponseDTO>> addMember(MemberRequestDto memberRequestDto) {
        Member member = this.memberMapper.toEntity(memberRequestDto);
        Member memberFromDB = this.memberRepository.save(member);
        MemberDataResponseDTO dto = this.memberMapper.toDto(memberFromDB);
        ResponseVO<MemberDataResponseDTO> responseVO = new ResponseVOBuilder<MemberDataResponseDTO>().addData(dto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseVO<MemberDataResponseDTO>> updateMember(MemberRequestDto memberRequestDto) {
        Member save = this.memberRepository.save(memberMapper.toEntity(memberRequestDto));
        ResponseVO<MemberDataResponseDTO> responseVO = new ResponseVOBuilder<MemberDataResponseDTO>().addData(this.memberMapper.toDto(save)).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ResponseVO<MemberDataResponseDTO>> getMemberById(String id) {
        return memberRepository.findById(id).map(member -> {
            MemberDataResponseDTO memberRequestDto = memberMapper.toDto(member);
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
    public ResponseEntity<ResponseVO<MemberDataResponseDTO>> findMemberByNumberPhone(String numberphone) {
        Member member = this.memberRepository.findByNumberPhone(numberphone).orElseThrow(() -> new EntityNotFoundException("Member Introuvable"));
        MemberDataResponseDTO memberDataResponseDTO = memberMapper.toDto(member);

        return new ResponseEntity<>(new ResponseVOBuilder<MemberDataResponseDTO>().addData(memberDataResponseDTO).build(), HttpStatus.OK);
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
        for (Member member : memberPage) {
            MemberDataResponseDTO memberDataResponseDTO = this.memberMapper.toDto(member);
            memberResponseDtos.add(memberDataResponseDTO);
        }
        return memberResponseDtos;
    }
}
