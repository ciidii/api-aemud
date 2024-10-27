package com.amud.io.aemudapi.services.impl;

import com.amud.io.aemudapi.dto.CommissionDto;
import com.amud.io.aemudapi.entities.Commission;
import com.amud.io.aemudapi.mappers.CommissionMapper;
import com.amud.io.aemudapi.repositories.CommissionRepository;
import com.amud.io.aemudapi.services.CommissionService;
import com.amud.io.aemudapi.utils.ResponseVO;
import com.amud.io.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommissionServiceImpl implements CommissionService {
    private final CommissionRepository commissionRepository;
    private final CommissionMapper commissionMapper;

    public CommissionServiceImpl(CommissionRepository commissionRepository, CommissionMapper commissionMapper) {
        this.commissionRepository = commissionRepository;
        this.commissionMapper = commissionMapper;
    }

    @Override
    public ResponseEntity<ResponseVO<CommissionDto>> addCommission(CommissionDto commissionDto) {
        Commission commission =  this.commissionMapper.toEntity(commissionDto);
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
        return new ResponseEntity<>(responseVO,HttpStatus.OK);
    }
}
