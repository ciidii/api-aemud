package com.amud.io.aemudapi.services.impl;

import com.amud.io.aemudapi.Specifications.MemberSpecification;
import com.amud.io.aemudapi.dto.YearOfSessionResponse;
import com.amud.io.aemudapi.entities.Member;
import com.amud.io.aemudapi.entities.YearOfSession;
import com.amud.io.aemudapi.exceptions.customeExceptions.EntityCannotBeDeletedException;
import com.amud.io.aemudapi.exceptions.customeExceptions.NoActiveSection;
import com.amud.io.aemudapi.mappers.YearOfSessionMapper;
import com.amud.io.aemudapi.repositories.MemberRepository;
import com.amud.io.aemudapi.repositories.YearOfSessionRepository;
import com.amud.io.aemudapi.services.YearOfSessionServices;
import com.amud.io.aemudapi.utils.ResponseVO;
import com.amud.io.aemudapi.utils.ResponseVOBuilder;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class YearOfSessionServicesImpl implements YearOfSessionServices {
    private final YearOfSessionRepository yearOfSessionRepository;
    private final YearOfSessionMapper yearOfSessionMapper;
    private final MemberRepository memberRepository;

    public YearOfSessionServicesImpl(YearOfSessionRepository yearOfSessionServices, YearOfSessionMapper yearOfSessionMapper, MemberRepository memberRepository) {
        this.yearOfSessionRepository = yearOfSessionServices;
        this.yearOfSessionMapper = yearOfSessionMapper;
        this.memberRepository = memberRepository;
    }


    @Override
    public ResponseEntity<ResponseVO<Void>> openNewSession(int year_) {
        //TODO: A revoir quand on modifier une année on défini comme année courant
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
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<YearOfSessionResponse>> getCurrentSession() {
        YearOfSession currentYearOfSession = this.yearOfSessionRepository.findCurrentSession().orElseThrow(() -> new NoActiveSection("No active session"));
        YearOfSessionResponse yearOfSessionResponse = this.yearOfSessionMapper.toDto(currentYearOfSession);
        ResponseVO<YearOfSessionResponse> responseVO = new ResponseVOBuilder<YearOfSessionResponse>().addData(yearOfSessionResponse).build();

        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<YearOfSessionResponse>> getAParticularYear(Long sessionid) {
        YearOfSession session = this.yearOfSessionRepository.findById(sessionid).orElseThrow(() -> new EntityNotFoundException("No session with id=" + sessionid));
        YearOfSessionResponse yearOfSessionResponse = this.yearOfSessionMapper.toDto(session);
        ResponseVO<YearOfSessionResponse> responseVO = new ResponseVOBuilder<YearOfSessionResponse>().addData(yearOfSessionResponse).build();

        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Boolean>> checkIfCanDeleteSession(Long sessionid) {
        if (this.yearOfSessionRepository.existsById(sessionid)) {
            Specification<Member> memberSpecification = MemberSpecification.hasYearOfRegistration(sessionid);
            List<Member> members = this.memberRepository.findAll(memberSpecification);
            ResponseVO<Boolean> responseVO;
            if (!members.isEmpty()) {
                responseVO = new ResponseVOBuilder<Boolean>().addData(false).build();
            } else {
                responseVO = new ResponseVOBuilder<Boolean>().addData(true).build();
            }
            return new ResponseEntity<>(responseVO, HttpStatus.OK);
        }
        throw new EntityNotFoundException("No session with id=" + sessionid);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> deleteSession(Long sessionid) {
        YearOfSession session = this.yearOfSessionRepository.findById(sessionid).orElseThrow(() -> new EntityNotFoundException("No active session"));
        ResponseEntity<ResponseVO<Boolean>> canDeleteSession = this.checkIfCanDeleteSession(sessionid);
        if (canDeleteSession.getBody().getData()) {
            this.yearOfSessionRepository.delete(session);
            return new ResponseEntity<>(new ResponseVOBuilder<Void>().success().build(), HttpStatus.OK);
        }
        throw new EntityCannotBeDeletedException("Impossible to delete session");
    }
}
