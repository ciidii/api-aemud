package com.amud.io.aemudapi.services.impl;

import com.amud.io.aemudapi.dto.CommissionDto;
import com.amud.io.aemudapi.dto.FilterDTO;
import com.amud.io.aemudapi.dto.MemberResponseDto;
import com.amud.io.aemudapi.entities.Commission;
import com.amud.io.aemudapi.entities.Member;
import com.amud.io.aemudapi.exceptions.customeExceptions.EntityCannotBeDeletedException;
import com.amud.io.aemudapi.mappers.CommissionMapper;
import com.amud.io.aemudapi.repositories.CommissionRepository;
import com.amud.io.aemudapi.repositories.MemberRepository;
import com.amud.io.aemudapi.services.CommissionService;
import com.amud.io.aemudapi.utils.ResponseVO;
import com.amud.io.aemudapi.utils.ResponseVOBuilder;
import com.amud.io.aemudapi.utils.Utils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommissionServiceImpl implements CommissionService {
    private final CommissionRepository commissionRepository;
    private final CommissionMapper commissionMapper;
    private final MemberRepository memberRepository;

    public CommissionServiceImpl(CommissionRepository commissionRepository, CommissionMapper commissionMapper, MemberRepository memberRepository) {
        this.commissionRepository = commissionRepository;
        this.commissionMapper = commissionMapper;
        this.memberRepository = memberRepository;
    }

    @Override
    public ResponseEntity<ResponseVO<CommissionDto>> addCommission(CommissionDto commissionDto) {
        Commission commission = this.commissionMapper.toEntity(commissionDto);
        commission = this.commissionRepository.save(commission);
        commissionDto = this.commissionMapper.toDto(commission);
        ResponseVO<CommissionDto> responseVO = new ResponseVOBuilder<CommissionDto>().addData(commissionDto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseVO<List<CommissionDto>>> getAllCommission() {
        List<CommissionDto> commissionDtos = new ArrayList<>();
        this.commissionRepository.findAll().forEach(commission -> {
            commissionDtos.add(this.commissionMapper.toDto(commission));
        });
        ResponseVO<List<CommissionDto>> responseVO = new ResponseVOBuilder<List<CommissionDto>>().addData(commissionDtos).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> deleteCommission(Long commissionID) {
        FilterDTO filterDTO = new FilterDTO(null, null, commissionID);
        Specification<Member> memberSpecification = Utils.makeFilterCriteriaSpec(null, null, filterDTO);
        List<Member> members = this.memberRepository.findAll(memberSpecification);
        if (members.isEmpty()) {
            this.commissionRepository.deleteById(commissionID);
            this.commissionRepository.deleteById(commissionID);
            return new ResponseEntity<>(new ResponseVOBuilder<Void>().success().build(), HttpStatus.OK);
        }
        throw new EntityCannotBeDeletedException("Impossible to delete Club");
    }

    @Override
    public ResponseEntity<ResponseVO<CommissionDto>> getSingleCommission(Long commissionID) {
        Commission commission = this.commissionRepository.findById(commissionID).orElseThrow(() -> new EntityNotFoundException("Pas de commission avec id" + commissionID));
        CommissionDto commissionDto = this.commissionMapper.toDto(commission);
        ResponseVO<CommissionDto> responseVO = new ResponseVOBuilder<CommissionDto>().addData(commissionDto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<CommissionDto>> updateCommission(CommissionDto commission) {
        this.commissionRepository.save(this.commissionMapper.toEntity(commission));
        ResponseVO<CommissionDto> responseVO = new ResponseVOBuilder<CommissionDto>().addData(commission).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }
}
