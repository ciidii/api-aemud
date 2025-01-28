package org.aemudapi.member.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.aemudapi.member.dtos.CommissionDto;
import org.aemudapi.member.dtos.FilterDTO;
import org.aemudapi.exceptions.customeExceptions.EntityCannotBeDeletedException;
import org.aemudapi.commission.entity.Commission;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.mapper.CommissionMapper;
import org.aemudapi.member.repository.CommissionRepository;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.member.service.CommissionService;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.aemudapi.utils.Utils;
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
    public ResponseEntity<ResponseVO<Void>> deleteCommission(String commissionID) {
        FilterDTO filterDTO = FilterDTO.builder().commission(commissionID).build();
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
    public ResponseEntity<ResponseVO<CommissionDto>> getSingleCommission(String commissionID) {
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
