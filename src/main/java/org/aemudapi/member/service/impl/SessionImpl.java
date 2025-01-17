package org.aemudapi.member.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.aemudapi.member.dtos.YearOfSessionResponse;
import org.aemudapi.exceptions.customeExceptions.EntityCannotBeDeletedException;
import org.aemudapi.exceptions.customeExceptions.NoActiveSection;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.mapper.YearOfSessionMapper;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.member.repository.YearOfSessionRepository;
import org.aemudapi.member.specifications.MemberSpecification;
import org.aemudapi.member.service.Session;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionImpl implements Session {
    private final YearOfSessionRepository yearOfSessionRepository;
    private final YearOfSessionMapper yearOfSessionMapper;
    private final MemberRepository memberRepository;

    public SessionImpl(YearOfSessionRepository yearOfSessionServices, YearOfSessionMapper yearOfSessionMapper, MemberRepository memberRepository) {
        this.yearOfSessionRepository = yearOfSessionServices;
        this.yearOfSessionMapper = yearOfSessionMapper;
        this.memberRepository = memberRepository;
    }


    @Override
    public ResponseEntity<ResponseVO<Void>> openNewSession(int year_) {
        //TODO: A revoir quand on modifier une année on défini comme année courant
        org.aemudapi.member.entity.Session session = new org.aemudapi.member.entity.Session();
        session.setYear_(year_);
        session.setCurrentYear(true);
        this.yearOfSessionRepository.updateCurrentYear();
        this.yearOfSessionRepository.save(session);
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
        org.aemudapi.member.entity.Session currentSession = this.yearOfSessionRepository.findCurrentSession().orElseThrow(() -> new NoActiveSection("No active session"));
        YearOfSessionResponse yearOfSessionResponse = this.yearOfSessionMapper.toDto(currentSession);
        ResponseVO<YearOfSessionResponse> responseVO = new ResponseVOBuilder<YearOfSessionResponse>().addData(yearOfSessionResponse).build();

        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<YearOfSessionResponse>> getAParticularYear(Long sessionid) {
        org.aemudapi.member.entity.Session session = this.yearOfSessionRepository.findById(sessionid).orElseThrow(() -> new EntityNotFoundException("No session with id=" + sessionid));
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
        org.aemudapi.member.entity.Session session = this.yearOfSessionRepository.findById(sessionid).orElseThrow(() -> new EntityNotFoundException("No active session"));
        ResponseEntity<ResponseVO<Boolean>> canDeleteSession = this.checkIfCanDeleteSession(sessionid);
        if (canDeleteSession.getBody().getData()) {
            this.yearOfSessionRepository.delete(session);
            return new ResponseEntity<>(new ResponseVOBuilder<Void>().success().build(), HttpStatus.OK);
        }
        throw new EntityCannotBeDeletedException("Impossible to delete session");
    }
}
