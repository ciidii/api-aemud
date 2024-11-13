package com.amud.io.aemudapi.services.impl;

import com.amud.io.aemudapi.dto.BourseDTO;
import com.amud.io.aemudapi.entities.Bourse;
import com.amud.io.aemudapi.mappers.BourseMapper;
import com.amud.io.aemudapi.repositories.BourseRepository;
import com.amud.io.aemudapi.services.BourseService;
import com.amud.io.aemudapi.utils.ResponseVO;
import com.amud.io.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BourseServiceImpl implements BourseService {
    private final BourseMapper bourseMapper;
    private final BourseRepository bourseRepository;

    public BourseServiceImpl(BourseMapper bourseMapper, BourseRepository bourseRepository) {
        this.bourseMapper = bourseMapper;
        this.bourseRepository = bourseRepository;
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
}
