package org.aemudapi.member.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.aemudapi.member.dtos.BourseDTO;
import org.aemudapi.exceptions.customeExceptions.EntityCannotBeDeletedException;
import org.aemudapi.member.entity.Bourse;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.mapper.BourseMapper;
import org.aemudapi.member.repository.BourseRepository;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.member.service.BourseService;
import org.aemudapi.member.specifications.MemberSpecification;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BourseServiceImpl implements BourseService {
    private final BourseMapper bourseMapper;
    private final BourseRepository bourseRepository;
    private final MemberRepository memberRepository;

    public BourseServiceImpl(BourseMapper bourseMapper, BourseRepository bourseRepository, MemberRepository memberRepository) {
        this.bourseMapper = bourseMapper;
        this.bourseRepository = bourseRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public ResponseEntity<ResponseVO<BourseDTO>> addBourse(BourseDTO bourseDTO) {
        Bourse bourse = bourseMapper.toEntity(bourseDTO);
        BourseDTO dto = this.bourseMapper.toDTO(this.bourseRepository.save(bourse));
        ResponseVO<BourseDTO> responseVO = new ResponseVOBuilder<BourseDTO>().addData(dto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<List<BourseDTO>>> getAllBourse() {
        List<Bourse> bourses = this.bourseRepository.findAll();
        List<BourseDTO> bourseDTOS = this.bourseMapper.toDTO(bourses);
        ResponseVO<List<BourseDTO>> responseVO = new ResponseVOBuilder<List<BourseDTO>>().addData(bourseDTOS).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> deleteBourse(String bourseId) {
        if (checkIfCanDeleteBourse(bourseId).getBody().getData()) {
            this.bourseRepository.deleteById(bourseId);
            return new ResponseEntity<>(new ResponseVOBuilder<Void>().success().build(), HttpStatus.OK);
        }
        throw new EntityCannotBeDeletedException("Impossible to delete bourse");
    }

    @Override
    public ResponseEntity<ResponseVO<Boolean>> checkIfCanDeleteBourse(String bourseID) {
        if (this.bourseRepository.existsById(bourseID)) {
            Specification<Member> memberSpecification = MemberSpecification.hasBourse(bourseID);
            List<Member> members = this.memberRepository.findAll(memberSpecification);
            ResponseVO<Boolean> responseVO;
            if (!members.isEmpty()) {
                responseVO = new ResponseVOBuilder<Boolean>().addData(false).build();
            } else {
                responseVO = new ResponseVOBuilder<Boolean>().addData(true).build();
            }
            return new ResponseEntity<>(responseVO, HttpStatus.OK);
        }
        throw new EntityNotFoundException("No Bourse with id=" + bourseID);
    }

    @Override
    public ResponseEntity<ResponseVO<BourseDTO>> findBourseById(String bourseId) {
        Bourse bourse = this.bourseRepository.findById(bourseId).orElseThrow(EntityNotFoundException::new);
        BourseDTO dto = this.bourseMapper.toDTO(bourse);
        ResponseVO<BourseDTO> responseVO = new ResponseVOBuilder<BourseDTO>().addData(dto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Double>> getMemberOfContributionAmount(String numberPhone) {
        Member member = this.memberRepository.findByNumberPhone(numberPhone).orElseThrow(EntityNotFoundException::new);

        return new ResponseEntity<>(new ResponseVOBuilder<Double>().addData(member.getBourse().getMontant()).build(), HttpStatus.OK);
    }
}
