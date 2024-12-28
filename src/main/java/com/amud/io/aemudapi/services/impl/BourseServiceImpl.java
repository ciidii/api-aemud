package com.amud.io.aemudapi.services.impl;

import com.amud.io.aemudapi.Specifications.MemberSpecification;
import com.amud.io.aemudapi.dto.BourseDTO;
import com.amud.io.aemudapi.entities.Bourse;
import com.amud.io.aemudapi.entities.Member;
import com.amud.io.aemudapi.exceptions.customeExceptions.EntityCannotBeDeletedException;
import com.amud.io.aemudapi.mappers.BourseMapper;
import com.amud.io.aemudapi.mappers.MemberMapper;
import com.amud.io.aemudapi.repositories.BourseRepository;
import com.amud.io.aemudapi.repositories.MemberRepository;
import com.amud.io.aemudapi.services.BourseService;
import com.amud.io.aemudapi.utils.ResponseVO;
import com.amud.io.aemudapi.utils.ResponseVOBuilder;
import jakarta.persistence.EntityNotFoundException;
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
    public ResponseEntity<ResponseVO<Void>> deleteBourse(Long bourseId) {
        if (checkIfCanDeleteBourse(bourseId).getBody().getData()) {
            this.bourseRepository.deleteById(bourseId);
            return new ResponseEntity<>(new ResponseVOBuilder<Void>().success().build(), HttpStatus.OK);
        }
        throw new EntityCannotBeDeletedException("Impossible to delete bourse");
    }

    @Override
    public ResponseEntity<ResponseVO<Boolean>> checkIfCanDeleteBourse(Long bourseID) {
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
    public ResponseEntity<ResponseVO<BourseDTO>> findBourseById(Long bourseId) {
        Bourse bourse = this.bourseRepository.findById(bourseId).orElseThrow(EntityNotFoundException::new);
        BourseDTO dto = this.bourseMapper.toDTO(bourse);
        ResponseVO<BourseDTO> responseVO = new ResponseVOBuilder<BourseDTO>().addData(dto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }
}
