package com.amud.io.aemudapi.services.impl;

import com.amud.io.aemudapi.dto.YearOfSessionResponse;
import com.amud.io.aemudapi.entities.YearOfSession;
import com.amud.io.aemudapi.mappers.YearOfSessionMapper;
import com.amud.io.aemudapi.repositories.YearOfSessionRepository;
import com.amud.io.aemudapi.services.YearOfSessionServices;
import com.amud.io.aemudapi.utils.ResponseVO;
import com.amud.io.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class YearOfSessionServicesImpl implements YearOfSessionServices {
    private final YearOfSessionRepository yearOfSessionRepository;
    private final YearOfSessionMapper yearOfSessionMapper;
    public YearOfSessionServicesImpl(YearOfSessionRepository yearOfSessionServices, YearOfSessionMapper yearOfSessionMapper) {
        this.yearOfSessionRepository = yearOfSessionServices;
        this.yearOfSessionMapper = yearOfSessionMapper;
    }


    @Override
    public ResponseEntity<ResponseVO<Void>> openNewSession(int year_) {
        YearOfSession yearOfSession = new YearOfSession();
        yearOfSession.setYear_(year_);
        yearOfSession.setCurrentYear(true);
        this.yearOfSessionRepository.updateCurrentYear();
        this.yearOfSessionRepository.save(yearOfSession);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().success().build(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseVO<List<YearOfSessionResponse>>> getAllYears() {
        List<YearOfSessionResponse> yearOfSessionResponses = new ArrayList<>();
        this.yearOfSessionRepository.findAll().forEach(yearOfSession -> {
            yearOfSessionResponses.add(this.yearOfSessionMapper.toDto(yearOfSession));
        });
        ResponseVO<List<YearOfSessionResponse>> responseVO = new ResponseVOBuilder<List<YearOfSessionResponse>>().addData(yearOfSessionResponses).build();
        return new ResponseEntity<>(responseVO,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<YearOfSessionResponse>> getCurrentSession() {
        YearOfSession currentYearOfSession = this.yearOfSessionRepository.findCurrentSession();
        YearOfSessionResponse yearOfSessionResponse = this.yearOfSessionMapper.toDto(currentYearOfSession);
        ResponseVO<YearOfSessionResponse> responseVO = new ResponseVOBuilder<YearOfSessionResponse>().addData(yearOfSessionResponse).build();

        return new ResponseEntity<>(responseVO,HttpStatus.OK);
    }
}
